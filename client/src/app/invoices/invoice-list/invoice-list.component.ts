import { Component, OnInit } from '@angular/core';
import { MatDialog, MatTableDataSource, PageEvent } from '@angular/material';

import { Invoice, InvoiceStatus } from '../invoice';
import { InvoiceService } from '../invoice.service';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.sass']
})
export class InvoiceListComponent implements OnInit {

  invoiceStatus = InvoiceStatus;

  displayedColumns = ['id', 'client', 'issue_date', 'check_date', 'status', 'inspector'];
  dataSource = new MatTableDataSource<Invoice>();
  pageNumber = 1;
  pageSize = 3;
  length: number;

  pageEvent: PageEvent;

  constructor(private invoiceService: InvoiceService,
              private dialog: MatDialog) {
  }

  openInvoiceDetail(id?: number) {
    const dialogRef = this.dialog.open(DocHolderComponent, {
      panelClass: 'app-document',
      data: {
        type: 'invoice',
        id: id
      }
    });
  }

  getInvoices() {
    this.invoiceService.getInvoices(this.pageNumber, this.pageSize)
      .subscribe(invoices => this.dataSource.data = invoices);
  }

  size() {
    this.invoiceService.size()
      .subscribe(length => this.length = length);
  }

  loadInvoices(event?: PageEvent) {
    this.pageNumber = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    this.getInvoices();
    this.size();
  }

  ngOnInit() {
    this.getInvoices();
    this.size();
  }

}
