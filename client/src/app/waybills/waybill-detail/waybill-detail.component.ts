import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { map, startWith, tap } from 'rxjs/operators';
import { animate, style, transition, trigger } from '@angular/animations';

import {} from '@types/googlemaps';

import { Invoice } from '../../invoices/invoice';
import { Waybill, WaybillStatus } from '../waybill';
import { WaybillService } from '../waybill.service';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';
import { WaybillCheckpoint } from '../waybill-checkpoint';
import { Checkpoint } from '../checkpoint';
import { RoleGuard, User, UserService } from '../../users';
import { Warehouse, WarehouseService } from '../../warehouses';
import { Car } from '../../cars/car';
import { CarService } from '../../cars/car.service';
import { ProgressDialogComponent } from '../../progress-dialog/progress-dialog.component';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';
import { Utils } from '../../utils';

@Component({
  selector: 'app-waybill-detail',
  templateUrl: './waybill-detail.component.html',
  styleUrls: ['./waybill-detail.component.sass'],
  animations: [
    trigger('shrinkIn', [
      transition('void => *', [
        style({
          height: '0',
          opacity: 0
        }),
        animate('0.35s ease-out')
      ]),
      transition('* => void', [
        animate('0.25s ease-in', style({
          height: '0',
          opacity: 0
        }))
      ])
    ])
  ]
})
export class WaybillDetailComponent implements OnInit {

  @ViewChild('map') mapElement: ElementRef;
  map: google.maps.Map;

  wForm: FormGroup;

  @Input() invoice: Invoice;
  waybill = new Waybill();

  edit: boolean;
  editAvailability: boolean;

  isMapLoaded: boolean;
  isDirectionLoaded: boolean;

  drivers: User[];
  cars: Car[];
  warehouses: Warehouse[];

  filteredCheckpoints: Checkpoint[];

  constructor(private fb: FormBuilder,
              private waybillService: WaybillService,
              private iconRegistry: MatIconRegistry,
              private sanitizer: DomSanitizer,
              private dialog: MatDialog,
              private parentDialogRef: MatDialogRef<DocHolderComponent>,
              private carService: CarService,
              public roleGuard: RoleGuard,
              private userService: UserService,
              private warehouseService: WarehouseService) {

    iconRegistry.addSvgIcon(
      'routes',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icon/routes.svg')
    );
  }

  displayCheckpointFn(checkpoint: Checkpoint): string {
    return checkpoint && checkpoint.name;
  }

  compareUser(user1: User, user2: User): boolean {
    return user1 && user2 && user1.id === user2.id;
  }

  compareCar(car1: Car, car2: Car): boolean {
    return car1 && car2 && car1.id === car2.id;
  }

  toggleEdit(): void {
    if (!this.edit) {
      this.edit = true;

      if (this.roleGuard.isOwner() || this.roleGuard.isManager()) {
        this.driver.enable();
        this.car.enable();
        this.from.enable();
        this.to.enable();
        this.status.enable();

        this.waybillCheckpoints.controls.forEach(control => {
          if (!control.get('checked').value && control.get('checkpoint').value) {
            control.get('checked').enable();
          }
          if (!control.get('checked').value) {
            control.get('checkpoint').enable();
          }
        });
      }
      if (this.roleGuard.isDriver()) {
        this.waybillCheckpoints.controls.forEach(control => {
          if (!control.get('checked').value && control.get('checkpoint').value) {
            control.get('checked').enable();
          }
          if (!control.get('checked').value) {
            control.get('checkpoint').enable();
          }
        });
      }
    }
  }

  getCheckDate(index: number): string {
    if (this.waybill.waybillCheckpoints && this.waybill.waybillCheckpoints[index] && this.waybill.waybillCheckpoints[index].checkDate) {
      return this.waybill.waybillCheckpoints[index].checkDate;
    } else {
      return Utils.dateToString(new Date());
    }
  }

  submit() {
    if (!this.waybill.status) {
      const dialogRef = this.dialog.open(ProgressDialogComponent, {
        disableClose: true,
        data: {
          text: 'Saving waybill. Please wait...'
        }
      });

      this.waybill.invoiceId = this.invoice.id;
      this.waybill.driver = this.wForm.value.driver;
      this.waybill.car = this.wForm.value.car;
      this.waybill.from = this.wForm.value.from;
      this.waybill.to = this.wForm.value.to;
      this.waybill.waybillCheckpoints = this.wForm.value.waybillCheckpoints
        .filter(waybillCheckpoint => waybillCheckpoint.checkpoint);

      this.calcDistance((response: google.maps.DistanceMatrixResponse, status: google.maps.DistanceMatrixStatus) => {
        if (status === google.maps.DistanceMatrixStatus.OK) {
          this.waybill.distance = response.rows[0].elements[0].distance.value;

          this.waybillService.registerWaybill(this.waybill)
            .subscribe(waybill => {
              dialogRef.close();
              setTimeout(() => {
                this.parentDialogRef.close(waybill);
              }, 300);
            });
        } else {
          console.error(status);
          dialogRef.close();
          setTimeout(() => {
            this.dialog.open(ConfirmDialogComponent, {
              data: {
                text: 'Could not save waybill. Please, try again.',
                trueAction: 'Ok',
                isAlert: true
              }
            });
          }, 300);
        }
      });
    } else if (this.waybill.status === WaybillStatus.STARTED) {
      this.waybill.waybillCheckpoints = this.wForm.value.waybillCheckpoints
        .filter(waybillCheckpoint => waybillCheckpoint.checkpoint);
      this.waybillService.checkWaybill(this.waybill)
        .subscribe(waybill => this.parentDialogRef.close(waybill));
    }
  }

  cancel() {
    console.log('Waybill canceled');
  }

  shouldShowAddBtn(index: number): boolean {
    return index === (this.waybillCheckpoints.controls.length - 1)
      && this.waybillCheckpoints.controls[index].get('checkpoint').value
      && this.edit
      && (this.roleGuard.isOwner() || this.roleGuard.isManager());
  }

  shouldShowRemoveBtn(): boolean {
    return this.waybillCheckpoints.controls.length > 1
      && this.edit
      && (this.roleGuard.isOwner() || this.roleGuard.isManager());
  }

  compareWarehouse(warehouse1: Warehouse, warehouse2: Warehouse) {
    return warehouse1 && warehouse2 && warehouse1.id === warehouse2.id;
  }

  validCheckpoint(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      const valid = typeof control.value === 'object' || !control.value;
      return valid ? null : {'validCheckpoint': {value: control.value}};
    };
  }

  getDrivers(): void {
    this.userService.getAvailableDrivers()
      .subscribe(drivers => {
        this.drivers = drivers;

        if (this.waybill.driver) {
          this.drivers.push(this.waybill.driver);
        }
      });
  }

  getCars(): void {
    this.carService.getCars()
      .subscribe(cars => this.cars = cars);
  }

  // TODO: Change call to get all warehouses.
  getWarehouses(): void {
    this.warehouseService.getWarehouses(1, 100)
      .subscribe(warehouses => this.warehouses = warehouses);
  }

  calcDistance(callback) {
    const service = new google.maps.DistanceMatrixService();
    service.getDistanceMatrix({
      origins: [{lat: this.waybill.from.lat, lng: this.waybill.from.lng}],
      destinations: [{lat: this.waybill.to.lat, lng: this.waybill.to.lng}],
      travelMode: google.maps.TravelMode.DRIVING,
      unitSystem: google.maps.UnitSystem.METRIC
    }, callback);
  }

  searchPlace(term: string): void {
    const service = new google.maps.places.AutocompleteService();
    service.getPlacePredictions({input: term, types: ['(cities)']},
      (result: google.maps.places.AutocompletePrediction[], status: google.maps.places.PlacesServiceStatus) => {
        if (status === google.maps.places.PlacesServiceStatus.OK) {
          this.filteredCheckpoints = result.map(prediction => {
            const checkpoint = new Checkpoint();
            checkpoint.placeId = prediction.place_id;
            checkpoint.name = prediction.structured_formatting.main_text;
            checkpoint.additionName = prediction.structured_formatting.secondary_text;
            return checkpoint;
          });
        } else {
          console.error(status);
        }
      });
  }

  loadLocation(checkpoint: Checkpoint): void {
    if (!checkpoint.placeId) {
      return;
    }

    const geocoder = new google.maps.Geocoder();
    geocoder.geocode({placeId: checkpoint.placeId}, (result: google.maps.GeocoderResult[], status: google.maps.GeocoderStatus) => {
      if (status === google.maps.GeocoderStatus.OK) {
        checkpoint.lat = result[0].geometry.location.lat();
        checkpoint.lng = result[0].geometry.location.lng();
      } else {
        console.error(status);
      }
    });
  }

  loadDirection() {
    let currentWaybill: Waybill;
    /*if (this.wForm.value.from && this.wForm.value.to) {
      currentWaybill = this.wForm.value;
    } else */
    if (this.waybill.from && this.waybill.to) {
      currentWaybill = this.waybill;
    } else {
      return;
    }

    this.isDirectionLoaded = true;

    const directionService = new google.maps.DirectionsService();
    const directionDisplay = new google.maps.DirectionsRenderer();

    const waypoints: google.maps.DirectionsWaypoint[] = [];
    currentWaybill.waybillCheckpoints.forEach(waybillCheckpoint => {
      if (waybillCheckpoint.checkpoint) {
        waypoints.push({
          location: {
            lat: waybillCheckpoint.checkpoint.lat,
            lng: waybillCheckpoint.checkpoint.lng
          }, stopover: false
        });
      }
    });

    console.log(waypoints);

    directionDisplay.setMap(this.map);
    directionService.route({
      origin: {lat: currentWaybill.from.lat, lng: currentWaybill.from.lng},
      destination: {lat: currentWaybill.to.lat, lng: currentWaybill.to.lng},
      waypoints: waypoints,
      optimizeWaypoints: true,
      travelMode: google.maps.TravelMode.DRIVING
    }, (result: google.maps.DirectionsResult, status: google.maps.DirectionsStatus) => {
      if (status === google.maps.DirectionsStatus.OK) {
        directionDisplay.setDirections(result);
      } else {
        console.error(result);
      }
    });
  }

  setMap(event) {
    this.map = event.map;
  }

  loadMap(): void {
    if (!this.isMapLoaded) {
      this.isMapLoaded = true;

      const mapOptions: google.maps.MapOptions = {
        gestureHandling: 'cooperative',
        center: {lat: 53.8854032, lng: 27.5518797},
        zoom: 12
      };
      this.map.setOptions(mapOptions);
    }

    if (!this.isDirectionLoaded) {
      this.loadDirection();
    }
  }

  get driver(): FormControl {
    return this.wForm.controls.driver as FormControl;
  }

  get car(): FormControl {
    return this.wForm.controls.car as FormControl;
  }

  get from(): FormControl {
    return this.wForm.controls.from as FormControl;
  }

  get waybillCheckpoints(): FormArray {
    return this.wForm.controls.waybillCheckpoints as FormArray;
  }

  get to(): FormControl {
    return this.wForm.controls.to as FormControl;
  }

  get status(): FormControl {
    return this.wForm.controls.status as FormControl;
  }

  addCheckpoint(waybillCheckpoint?: WaybillCheckpoint) {
    const group: FormGroup = this.fb.group({
      checked: [{
        value: waybillCheckpoint && waybillCheckpoint.checked,
        disabled: !(this.edit && (waybillCheckpoint && waybillCheckpoint.checkpoint) && !(waybillCheckpoint && waybillCheckpoint.checked))
      }],
      checkpoint: [{
        value: waybillCheckpoint && waybillCheckpoint.checkpoint,
        disabled: !this.edit
      }, [this.validCheckpoint(), Validators.required]]
    });

    group.controls['checkpoint'].valueChanges
      .pipe(
        startWith({} as Checkpoint),
        tap(checkpoint => {
          if (checkpoint && typeof checkpoint === 'object') {
            this.loadLocation(checkpoint);
          }
        }),
        map(checkpoint => checkpoint && typeof checkpoint === 'object' ? checkpoint.name : checkpoint)
      )
      .subscribe(name => name && this.searchPlace(name as string));

    this.waybillCheckpoints.push(group);
  }

  deleteCheckpoint(index: number) {
    this.waybillCheckpoints.removeAt(index);
  }

  initForm() {
    this.wForm = this.fb.group({
      driver: [{value: '', disabled: true}, Validators.required],
      car: [{value: '', disabled: true}, Validators.required],
      from: [{value: '', disabled: true}, Validators.required],
      to: [{value: '', disabled: true}, Validators.required],
      waybillCheckpoints: this.fb.array([]),
      status: [{value: '', disabled: true}]
    });
  }

  ngOnInit() {
    this.initForm();

    this.getDrivers();
    this.getCars();
    this.getWarehouses();

    if (this.invoice.waybill) {
      this.waybill = this.invoice.waybill;
      this.driver.setValue(this.waybill.driver);
      this.car.setValue(this.waybill.car);
      this.from.setValue(this.waybill.from);
      this.to.setValue(this.waybill.to);
      this.waybill.waybillCheckpoints.forEach(waybillCheckpoint => this.addCheckpoint(waybillCheckpoint));
    } else {
      this.addCheckpoint();
    }

    this.editAvailability = this.waybill.status !== WaybillStatus.COMPLETED;
  }
}
