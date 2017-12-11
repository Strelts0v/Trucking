import { Component, OnInit } from '@angular/core';
import { MatDialog, MatTableDataSource, PageEvent } from '@angular/material';

import { Waybill, WaybillStatus } from '../waybill';
import { WaybillService } from '../waybill.service';

@Component({
  selector: 'app-waybill-list',
  templateUrl: './waybill-list.component.html',
  styleUrls: ['./waybill-list.component.sass']
})
export class WaybillListComponent implements OnInit {

  waybillStatus = WaybillStatus;

  displayedColumns = ['from', 'to', 'car_number', 'invoice_id', 'issue_date'];
  dataSource = new MatTableDataSource<Waybill>();
  pageNumber = 1;
  pageSize = 3;
  length: number;

  pageEvent: PageEvent;

  constructor(private waybillService: WaybillService,
              private dialog: MatDialog) {
  }

  openWaybillDetail(id: number) {
  }

  getWaybills() {
    this.waybillService.getWaybills(this.pageNumber, this.pageSize)
      .subscribe(waybills => this.dataSource.data = waybills);
  }

  size() {
    this.waybillService.size()
      .subscribe(length => this.length = length);
  }

  loadWaybills(event?: PageEvent) {
    this.pageNumber = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    this.getWaybills();
    this.size();
  }

  ngOnInit() {
    this.getWaybills();
    this.size();
  }

}
