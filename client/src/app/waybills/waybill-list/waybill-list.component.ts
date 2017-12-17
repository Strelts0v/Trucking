import { Component, OnInit } from '@angular/core';
import { MatDialog, MatSnackBar, MatTableDataSource, PageEvent } from '@angular/material';

import { Waybill, WaybillStatus } from '../waybill';
import { WaybillService } from '../waybill.service';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';

@Component({
  selector: 'app-waybill-list',
  templateUrl: './waybill-list.component.html',
  styleUrls: ['./waybill-list.component.sass']
})
export class WaybillListComponent implements OnInit {

  waybillStatus = WaybillStatus;

  displayedColumns = ['from', 'to', 'car_number', 'invoice_number', 'issue_date'];
  dataSource = new MatTableDataSource<Waybill>();
  pageNumber = 1;
  pageSize = 10;
  length: number;

  pageEvent: PageEvent;

  constructor(private waybillService: WaybillService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) {
  }

  openWaybillDetail(id: number) {
    const dialogRef = this.dialog.open(DocHolderComponent, {
      panelClass: 'app-doc-holder',
      data: {
        type: 'waybill',
        id: id
      }
    });

    dialogRef.afterClosed().subscribe(waybill => {
      if (waybill) {
        this.size();
        this.getWaybills();
        this.snackBar.open('Waybill saved', '', {duration: 3000});
      }
    });
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
    this.size();
    this.getWaybills();
  }

  ngOnInit() {
    this.size();
    this.getWaybills();
  }

}
