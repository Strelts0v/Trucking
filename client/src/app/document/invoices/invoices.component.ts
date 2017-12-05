import { Component, OnInit } from '@angular/core';
import { MatDialog, MatTableDataSource, PageEvent } from '@angular/material';

import { Invoice } from '../entity/invoice';
import { InvoiceService } from '../invoice.service';
import { DocumentComponent } from '../document.component';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-invoices',
  templateUrl: './invoices.component.html',
  styleUrls: ['./invoices.component.sass']
})
export class InvoicesComponent implements OnInit {

  displayedColumns = ['id', 'client', 'issue_date', 'check_date', 'status', 'inspector'];
  dataSource = new MatTableDataSource<Invoice>();
  pageNumber = 1;
  pageSize = 3;
  length: number;

  pageEvent: PageEvent;

  constructor(private invoiceService: InvoiceService,
              public dialog: MatDialog) {
  }

  openInvoiceDetail(id?: number) {
    const dialogRef = this.dialog.open(DocumentComponent, {
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
