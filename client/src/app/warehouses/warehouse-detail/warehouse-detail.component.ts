import {Component, Inject} from '@angular/core';
import {MatDatepickerModule, MatDialogRef, MAT_DIALOG_DATA, MatDialog} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {Warehouse} from './../warehouse';
import {WarehouseService} from './../warehouse.service';
import {ConfirmDialogComponent} from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-warehouse-detail',
  templateUrl: 'warehouse-detail.component.html',
  styleUrls: ['./warehouse-detail.component.sass'],
})

export class WarehouseDetailComponent {

  warehouse: Warehouse;

  deletedWarehouseId: number;

  constructor(
    public dialogRef: MatDialogRef<WarehouseDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private warehouseService: WarehouseService,
    private dialog: MatDialog) {
      this.warehouse = <Warehouse> data.warehouse;
    }

  saveWarehouseDetail(): void {
    const address = `${this.warehouse.country}, ${this.warehouse.city}, ${this.warehouse.street}, ${this.warehouse.house}`;
    const geocoder = new google.maps.Geocoder();
    geocoder.geocode({address: address}, (result: google.maps.GeocoderResult[], status: google.maps.GeocoderStatus) => {
      if (status === google.maps.GeocoderStatus.OK) {
        console.log(`${result}`);
        this.warehouse.lat = result[0].geometry.location.lat();
        this.warehouse.lng = result[0].geometry.location.lng();

        this.dialogRef.close(this.warehouse);
      } else {
        this.dialog.open(ConfirmDialogComponent, {
          data: {
            text: 'Could not save warehouse. Please, try again.',
            trueAction: 'Ok',
            isAlert: true
          }
        });
        console.error(status);
      }
    });
  }

  deleteWarehouse() {
    this.log(`Deleting client ${JSON.stringify(this.warehouse)}`);
    this.deletedWarehouseId = this.warehouse.id;
    this.warehouseService.deleteWarehouse(this.warehouse)
      .subscribe(_ => {
        this.dialogRef.close();
      });
  }

  private log(message: string) {
    console.log('WarehouseDetailComponent: ' + message);
  }
}
