import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatDialog, MatTableDataSource, PageEvent } from '@angular/material';
import { Warehouse } from '../warehouse';
import { WarehouseService } from '../warehouse.service';
import { WarehouseDetailComponent} from '../warehouse-detail/warehouse-detail.component';

@Component({
  selector: 'app-warehouse-list',
  templateUrl: './warehouse-list.component.html',
  styleUrls: ['./warehouse-list.component.sass']
})
export class WarehouseListComponent implements OnInit {

  displayedColumns = ['name', 'country', 'city', 'street', 'house'];
  dataSource = new MatTableDataSource<Warehouse>();
  pageNumber = 1;
  pageSize = 10;
  length: number;

  pageEvent: PageEvent;

  constructor(
    private warehouseService: WarehouseService,
    private dialog: MatDialog) {
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  getWarehouses() {
    this.warehouseService.getWarehouses(this.pageNumber, this.pageSize)
      .subscribe(warehouses => this.dataSource.data = warehouses);
  }

  size() {
    this.warehouseService.getWarehouseCount()
    .subscribe(length => this.length = length);
  }

  loadWarehouses(event?: PageEvent) {
    this.pageNumber = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    this.getWarehouses();
    this.size();
  }

  ngOnInit() {
    this.getWarehouses();
    this.size();
  }

  openWarehouseDetail(id?: number, warehouse?: Warehouse) {
    let isEditable = false;
    warehouse == null ? warehouse = new Warehouse() : isEditable = true;

    const dialogRef = this.dialog.open(WarehouseDetailComponent, {
      width: '500px',
      data: { warehouse: warehouse }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      warehouse = <Warehouse> result.warehouse;

      if (isEditable) {
        this.log(`EDIT ${JSON.stringify(warehouse)}`);
        this.warehouseService.updateWarehouse(warehouse);
      } else {
        this.log(`ADD ${JSON.stringify(warehouse)}`);
        this.warehouseService.addWarehouse(warehouse)
          .subscribe(client => {
            const warehouses = this.dataSource.data;
            this.log(`ADD ${JSON.stringify(warehouse)}`);
            warehouses.push(warehouse);
            this.dataSource.data = warehouses;
          });
      }
    });
  }

  private getWarehouseById(id: number): Warehouse {
    const warehouses = this.dataSource.data;
    return warehouses.filter(warehouse => warehouse.id === id)[0];
  }

  private log(message: string) {
    console.log('WarehouseListComponent: ' + message);
  }
}
