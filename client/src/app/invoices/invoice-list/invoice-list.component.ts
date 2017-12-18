import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatPaginator, MatSnackBar, MatTableDataSource, PageEvent } from '@angular/material';
import { animate, keyframes, state, style, transition, trigger } from '@angular/animations';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

import { Invoice, InvoiceStatus } from '../invoice';
import { InvoiceService } from '../invoice.service';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';
import { RoleGuard } from '../../users';
import { SearchService } from '../../main/search-bar/search.service';
import { InvoiceSearchCriteria } from '../invoice-search/invoice-search-criteria';
import { Utils } from '../../utils';

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

  @ViewChild('paginator') paginator: MatPaginator;

  invoiceStatus = InvoiceStatus;

  displayedColumns = ['number', 'client', 'issue_date', 'check_date', 'status', 'inspector'];
  dataSource = new MatTableDataSource<Invoice>();
  pageNumber = 1;
  pageSize = 10;
  length: number;

  searchCriteria: InvoiceSearchCriteria;

  constructor(private invoiceService: InvoiceService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar,
              public roleGuard: RoleGuard,
              private searchService: SearchService) {
  }

  openInvoiceDetail(id?: number): void {
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

  getInvoices(): void {
    this.invoiceService.getInvoices(this.pageNumber, this.pageSize)
      .subscribe(invoices => this.dataSource.data = invoices);
  }

  size(): void {
    this.invoiceService.size()
      .subscribe(length => this.length = length);
  }

  getSearchInvoices(): void {
    this.invoiceService.searchInvoices(this.searchCriteria, this.pageNumber, this.pageSize)
      .subscribe(invoices => {
        this.dataSource.data = invoices;
      });
  }

  searchSize(): void {
    this.invoiceService.searchSize(this.searchCriteria)
      .subscribe(length => this.length = length);
  }

  loadInvoices(event?: PageEvent): void {
    this.pageNumber = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    if (!this.searchCriteria) {
      this.getInvoices();
    } else {
      this.getSearchInvoices();
    }
  }

  onSearch(criteria: InvoiceSearchCriteria): void {
    this.pageNumber = 1;
    this.paginator.previousPage();
    if (criteria.checkDate || criteria.inspector || criteria.issueDate || criteria.status) {
      this.searchCriteria = criteria;
      this.searchCriteria.issueDate = Utils.dateToString(criteria.issueDate as Date);
      this.searchCriteria.checkDate = Utils.dateToString(criteria.checkDate as Date);
      this.searchSize();
      this.getSearchInvoices();
    } else if (this.searchCriteria) {
      this.searchCriteria = null;
      this.size();
      this.getInvoices();
    }
  }

  ngOnInit(): void {
    this.size();
    this.getInvoices();

    this.searchService.currentCriteria
      .pipe(
        debounceTime(500),
        distinctUntilChanged()
      )
      .subscribe(criteria => this.onSearch(criteria as InvoiceSearchCriteria));
  }

}
