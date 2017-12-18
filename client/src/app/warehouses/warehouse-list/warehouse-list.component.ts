import { Component, OnInit } from '@angular/core';
import { MatDialog, MatTableDataSource, PageEvent } from '@angular/material';

import { Warehouse } from '../warehouse'
import { WarehouseService } from '../warehouse.service';

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

  constructor(private warehouseService: WarehouseService,
              private dialog: MatDialog) {
  }

  openWarehouseDetail(){

  }

  getWarehouses() {
    this.warehouseService.getWarehouses(this.pageNumber, this.pageSize)
      .subscribe(warehouses => this.dataSource.data = warehouses)
  }

  size() {
    this.warehouseService.size()
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

}
