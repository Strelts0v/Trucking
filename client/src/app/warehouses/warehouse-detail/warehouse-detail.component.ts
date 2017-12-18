import {Component, Inject} from '@angular/core';
import {MatDatepickerModule, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {Warehouse} from './../warehouse';
import {WarehouseService} from './../warehouse.service';

@Component({
  selector: 'app-warehouse-detail',
  templateUrl: 'warehouse-detail.component.html',
  styleUrls: ['./warehouse-detail.component.sass'],
})

export class WarehouseDetailComponent {

  warehouse: Warehouse;

  constructor(
    public dialogRef: MatDialogRef<WarehouseDetailComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private warehouseService: WarehouseService) {
      this.warehouse = <Warehouse> data.warehouse;
    }

  cancelWarehouseDetail(): void {
    this.dialogRef.close();
  }

  deleteWarehouse() {
    this.log(`Deleting client ${JSON.stringify(this.warehouse)}`);
    this.warehouseService.deleteWarehouse(this.warehouse);
  }

  private log(message: string) {
    console.log('ClientDetailComponent: ' + message);
  }
}
