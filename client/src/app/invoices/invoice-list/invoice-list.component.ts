import { Component, OnInit } from '@angular/core';
import { MatDialog, MatSnackBar, MatTableDataSource, PageEvent } from '@angular/material';
import { animate, keyframes, state, style, transition, trigger } from '@angular/animations';

import { Invoice, InvoiceStatus } from '../invoice';
import { InvoiceService } from '../invoice.service';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';
import { RoleGuard } from '../../users';

@Component({
  selector: 'app-invoice-list',
  templateUrl: './invoice-list.component.html',
  styleUrls: ['./invoice-list.component.sass'],
  animations: [
    trigger('flyInFromBottom', [
      state('in', style({opacity: 1, transform: 'translateY(0)'})),
      transition('void => *', [
        animate(400, keyframes([
          style({opacity: 0, transform: 'translateY(80%)', offset: 0}),
          style({opacity: 1, transform: 'translateY(-20px)', offset: 0.3}),
          style({opacity: 1, transform: 'translateY(0)', offset: 1.0})
        ]))
      ])
    ])
  ]
})
export class InvoiceListComponent implements OnInit {

  invoiceStatus = InvoiceStatus;

  displayedColumns = ['id', 'client', 'issue_date', 'check_date', 'status', 'inspector'];
  dataSource = new MatTableDataSource<Invoice>();
  pageNumber = 1;
  pageSize = 10;
  length: number;

  pageEvent: PageEvent;

  constructor(private invoiceService: InvoiceService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar,
              public roleGuard: RoleGuard) {
  }

  openInvoiceDetail(id?: number) {
    const dialogRef = this.dialog.open(DocHolderComponent, {
      panelClass: 'app-doc-holder',
      data: {
        type: 'invoice',
        id: id
      }
    });

    dialogRef.afterClosed()
      .subscribe(invoice => {
        if (invoice) {
          this.size();
          this.getInvoices();
          this.snackBar.open('Consignment note saved', '', {duration: 3000});
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
    this.size();
    this.getInvoices();
  }

  ngOnInit() {
    this.size();
    this.getInvoices();
  }

}
