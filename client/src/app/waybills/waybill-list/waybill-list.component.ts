import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatPaginator, MatSnackBar, MatTableDataSource, PageEvent } from '@angular/material';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

import { Waybill, WaybillStatus } from '../waybill';
import { WaybillService } from '../waybill.service';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';
import { SearchService } from '../../main/search-bar/search.service';
import { WaybillSearchCriteria } from '../waybill-search/waybill-search-criteria';
import { Utils } from '../../utils';

@Component({
  selector: 'app-waybill-list',
  templateUrl: './waybill-list.component.html',
  styleUrls: ['./waybill-list.component.sass']
})
export class WaybillListComponent implements OnInit {

  @ViewChild('paginator') paginator: MatPaginator;

  waybillStatus = WaybillStatus;

  displayedColumns = ['from', 'to', 'car_number', 'invoice_number', 'issue_date'];
  dataSource = new MatTableDataSource<Waybill>();
  pageNumber = 1;
  pageSize = 10;
  length: number;

  searchCriteria: WaybillSearchCriteria;

  constructor(private waybillService: WaybillService,
              private dialog: MatDialog,
              private snackBar: MatSnackBar,
              private searchService: SearchService) {
  }

  openWaybillDetail(id: number): void {
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

  getWaybills(): void {
    this.waybillService.getWaybills(this.pageNumber, this.pageSize)
      .subscribe(waybills => this.dataSource.data = waybills);
  }

  size(): void {
    this.waybillService.size()
      .subscribe(length => this.length = length);
  }

  getSearchWaybills(): void {
    this.waybillService.searchWaybills(this.searchCriteria, this.pageNumber, this.pageSize)
      .subscribe(waybills => this.dataSource.data = waybills);
  }

  searchSize(): void {
    this.waybillService.searchSize(this.searchCriteria)
      .subscribe(length => this.length = length);
  }

  loadWaybills(event?: PageEvent): void {
    this.pageNumber = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    if (!this.searchCriteria) {
      this.getWaybills();
    } else {
      this.getSearchWaybills();
    }
  }

  onSearch(criteria: WaybillSearchCriteria): void {
    this.pageNumber = 1;
    this.paginator.previousPage();
    if (criteria.from || criteria.to || criteria.invoiceNumber || criteria.issueDate) {
      this.searchCriteria = criteria;
      this.searchCriteria.issueDate = criteria.issueDate && Utils.dateToString(criteria.issueDate as Date);
      this.searchSize();
      this.getSearchWaybills();
    } else if (this.searchCriteria) {
      this.searchCriteria = null;
      this.size();
      this.getWaybills();
    }
  }

  ngOnInit(): void {
    this.size();
    this.getWaybills();

    this.searchService.currentCriteria
      .pipe(
        debounceTime(500),
        distinctUntilChanged()
      )
      .subscribe(criteria => this.onSearch(criteria as WaybillSearchCriteria));
  }

}
