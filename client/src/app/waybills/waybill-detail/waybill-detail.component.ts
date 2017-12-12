import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, FormArray, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { map, startWith } from 'rxjs/operators';
import { animate, group, state, style, transition, trigger } from '@angular/animations';

import {} from '@types/googlemaps';
import DirectionsWaypoint = google.maps.DirectionsWaypoint;
import MapOptions = google.maps.MapOptions;
import PredictionSubstring = google.maps.places.PredictionSubstring;
import PredictionTerm = google.maps.places.PredictionTerm;

import { Invoice } from '../../invoices/invoice';
import { Waybill } from '../waybill';
import { WaybillService } from '../waybill.service';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';
import { WaybillCheckpoint } from '../waybill-checkpoint';
import { Checkpoint } from '../checkpoint';
import { User } from '../../users';
import { Warehouse } from '../../warehouses/warehouse';
import { Car } from '../../cars/car';
import { CarService } from '../../cars/car.service';

@Component({
  selector: 'app-waybill-detail',
  templateUrl: './waybill-detail.component.html',
  styleUrls: ['./waybill-detail.component.sass'],
  animations: [
    trigger('shrinkIn', [
      state('in', style({height: 'auto', transform: 'translateY(0)', opacity: 1})),
      transition('void => *', [
        style({height: 10, transform: 'translateY(-50px)', opacity: 0}),
        group([
          animate('0.3s ease', style({
            transform: 'translateY(0)',
            height: 'auto'
          })),
          animate('0.3s ease', style({
            opacity: 1
          }))
        ])
      ])
    ])
  ]
})
export class WaybillDetailComponent implements OnInit {

  @ViewChild('map') mapElement: ElementRef;
  map: google.maps.Map;

  firstMapLoad: boolean;

  wForm: FormGroup;

  @Input() invoice: Invoice;
  waybill = new Waybill();

  edit: boolean;

  drivers: User[] = [
    {
      id: 9,
      firstName: 'first name 9',
      lastName: 'last name 9',
      middleName: 'middle name 9',
      birthday: '',
      email: '',
      city: '',
      street: '',
      house: '',
      apartment: '',
      roles: [],
      login: '',
      password: '',
      passport: ''
    },
    {
      id: 11,
      firstName: 'first name 11',
      lastName: 'last name 11',
      middleName: 'middle name 11',
      birthday: '',
      email: '',
      city: '',
      street: '',
      house: '',
      apartment: '',
      roles: [],
      login: '',
      password: '',
      passport: ''
    }
  ];

  cars: Car[];

  warehouses: Warehouse[] = [
    {id: 1, name: 'Warehouse #1', country: 'Belarus', city: 'Minsk', street: 'Jasienina', house: 25, lat: 53.8393172, lng: 27.4090443},
    {
      id: 2,
      name: 'Warehouse #2',
      country: 'Russia',
      city: 'Moscow',
      street: 'Dmitrovskoye Shosse',
      house: 22,
      lat: 55.9413068,
      lng: 37.5442847
    },
    {id: 3, name: 'Warehouse #3', country: 'Russia', city: 'Ryazan', street: 'Moges', house: 12, lat: 54.627735, lng: 39.7195548}
  ];

  filteredCheckpoints: Checkpoint[];

  constructor(private fb: FormBuilder,
              private waybillService: WaybillService,
              private iconRegistry: MatIconRegistry,
              private sanitizer: DomSanitizer,
              private dialog: MatDialog,
              private parentDialogRef: MatDialogRef<DocHolderComponent>,
              private carService: CarService) {

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

  toggleEdit() {
    if (!this.edit) {
      this.edit = true;

      this.driver.enable();
      this.car.enable();
      this.from.enable();
      this.waybillCheckpoints.controls.forEach(control => {
        if (!control.get('checked').value && control.get('checkpoint').value) {
          control.get('checked').enable();
        }
        if (!control.get('checked').value) {
          control.get('checkpoint').enable();
        }
      });
      this.to.enable();
      this.status.enable();
    }
  }

  getCheckDate(index: number): string {
    if (this.waybill.waybillCheckpoints && this.waybill.waybillCheckpoints[index] && this.waybill.waybillCheckpoints[index].checkDate) {
      return this.waybill.waybillCheckpoints[index].checkDate;
    } else {
      const now = new Date();
      return `${now.getDay()}.${now.getMonth() + 1}.${now.getFullYear()}`;
    }
  }

  submit() {
    console.log('Waybill saved');
  }

  cancel() {
    console.log('Waybill canceled');
  }

  shouldShowAddBtn(index: number): boolean {
    return index === (this.waybillCheckpoints.controls.length - 1)
      && this.waybillCheckpoints.controls[index].get('checkpoint').value
      && this.edit;
  }

  shouldShowRemoveBtn(): boolean {
    return this.waybillCheckpoints.controls.length > 1
      && this.edit;
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
      }, this.validCheckpoint()]
    });

    group.controls['checkpoint'].valueChanges
      .pipe(
        startWith({} as Checkpoint),
        map(checkpoint => checkpoint && typeof checkpoint === 'object' ? checkpoint.name : checkpoint)
      )
      .subscribe(name => name && this.searchPlace(name as string));

    this.waybillCheckpoints.push(group);
  }

  deleteCheckpoint(index: number) {
    this.waybillCheckpoints.removeAt(index);
  }

  searchPlace(term: string) {
    const service = new google.maps.places.AutocompleteService();
    service.getPlacePredictions({input: term, types: ['(cities)']}, (result, status) => {
      if (status.toString() === 'OK') {
        this.filteredCheckpoints = result.map(prediction => {
          const checkpoint = new Checkpoint();
          checkpoint.place_id = prediction.place_id;
          checkpoint.name = (prediction as AutocompletePrediction).structured_formatting.main_text;
          checkpoint.addition = (prediction as AutocompletePrediction).structured_formatting.secondary_text;

          const geocoder = new google.maps.Geocoder();
          geocoder.geocode({placeId: checkpoint.place_id}, (result, status) => {
            if (status.toString() === 'OK') {
              checkpoint.lat = result[0].geometry.location.lat();
              checkpoint.lng = result[0].geometry.location.lng();
            }
          });

          return checkpoint;
        });

        console.log(this.filteredCheckpoints);
      }
    });
  }

  getCars(): void {
    this.carService.getCars()
      .subscribe(cars => this.cars = cars);
  }

  initForm() {
    this.wForm = this.fb.group({
      driver: [{value: '', disabled: true}, Validators.required],
      car: [{value: '', disabled: true}, Validators.required],
      from: [{value: '', disabled: true}, Validators.required],
      waybillCheckpoints: this.fb.array([]),
      to: [{value: '', disabled: true}, Validators.required],
      status: [{value: '', disabled: true}]
    });
  }

  loadMap() {
    if (!this.firstMapLoad) {
      this.firstMapLoad = true;

      google.maps.event.trigger(this.map, 'resize');
      this.loadDirection();
    }
  }

  loadDirection() {
    const currentWaybill: Waybill = this.wForm.value;
    if (!currentWaybill.from || !currentWaybill.to) {
      return;
    }

    const directionService = new google.maps.DirectionsService();
    const directionDisplay = new google.maps.DirectionsRenderer();

    const waypoints: DirectionsWaypoint[] = [];
    currentWaybill.waybillCheckpoints.forEach(waybillCheckpoint => {
      if (waybillCheckpoint.checkpoint) {
        waypoints.push({location: {lat: waybillCheckpoint.checkpoint.lat, lng: waybillCheckpoint.checkpoint.lng}, stopover: false});
      }
    });

    directionDisplay.setMap(this.map);
    directionService.route({
      origin: {lat: currentWaybill.from.lat, lng: currentWaybill.from.lng},
      destination: {lat: currentWaybill.to.lat, lng: currentWaybill.to.lng},
      waypoints: waypoints,
      optimizeWaypoints: true,
      travelMode: google.maps.TravelMode.DRIVING
    }, (result, status) => {
      if (status.toString() === 'OK') {
        directionDisplay.setDirections(result);
      }
    });
  }

  setMap(event) {
    this.map = event.map;

    const mapOptions: MapOptions = {
      gestureHandling: 'cooperative'
    };
    this.map.setOptions(mapOptions);
  }

  ngOnInit() {
    this.initForm();

    this.getCars();

    if (!this.invoice.waybill) {
      this.addCheckpoint();
    } else {
      this.waybill = this.invoice.waybill;
      this.driver.setValue(this.waybill.driver);
      this.car.setValue(this.waybill.car);
      this.from.setValue(this.waybill.from);
      this.waybill.waybillCheckpoints.forEach(waybillCheckpoint => this.addCheckpoint(waybillCheckpoint));
      this.to.setValue(this.waybill.to);
    }
  }
}

export interface AutocompletePrediction {
  description: string;
  matched_substrings: PredictionSubstring[];
  place_id: string;
  reference: string;
  structured_formatting: AutocompleteStructuredFormatting;
  terms: PredictionTerm[];
  types: string[];
}

export interface AutocompleteStructuredFormatting {
  main_text: string;
  main_text_matched_substrings: PredictionSubstring[];
  secondary_text: string;
}
