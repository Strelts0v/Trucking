import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatDialog, MatTableDataSource, PageEvent } from '@angular/material';
import { Warehouse } from '../warehouse';
import { WarehouseService } from '../warehouse.service';
import { WarehouseDetailComponent} from '../warehouse-detail/warehouse-detail.component';
import { SearchService } from '../../main/search-bar/search.service';
import { WarehouseSearchCriteria } from '../warehouse-search/warehouse-search-criteria';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';

@Component({
  selector: 'app-warehouse-list',
  templateUrl: './warehouse-list.component.html',
  styleUrls: ['./warehouse-list.component.sass'],
})
export class WarehouseListComponent implements OnInit {

  displayedColumns = ['name', 'country', 'city', 'street', 'house'];
  dataSource = new MatTableDataSource<Warehouse>();
  pageNumber = 1;
  pageSize = 10;
  length: number;

  pageEvent: PageEvent;
  searchCriteria: WarehouseSearchCriteria;

  constructor(
    private warehouseService: WarehouseService,
    private dialog: MatDialog,
    private searchService: SearchService) {
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit() {
    this.size();
    this.getWarehouses();

    this.searchService.currentCriteria
      .pipe(
        debounceTime(500),
        distinctUntilChanged()
      ).subscribe(criteria => this.onSearch(criteria as WarehouseSearchCriteria));
  }

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

  openWarehouseDetail(id?: number, warehouse?: Warehouse) {
    let isEditable = false;
    warehouse == null ? warehouse = new Warehouse() : isEditable = true;

    const dialogRef = this.dialog.open(WarehouseDetailComponent, {
      width: '500px',
      data: { warehouse: warehouse }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (dialogRef.componentInstance.deletedWarehouseId > 0) {
        this.log(`${result}`);
        this.getWarehouses();
        return;
      }

      warehouse = <Warehouse> result.warehouse;
      if (isEditable) {
        this.log(`EDIT ${JSON.stringify(warehouse)}`);
        this.warehouseService.updateWarehouse(warehouse);
      } else {
        this.log(`ADD ${JSON.stringify(warehouse)}`);
        this.warehouseService.addWarehouse(warehouse)
          .subscribe(ware => {
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

  getSearchWarehouses(): void {
    this.warehouseService.findWarehouses(this.searchCriteria)
      .subscribe(warehouses => {
        this.dataSource.data = warehouses;
        this.length = warehouses.length;
      });
  }

  onSearch(criteria: WarehouseSearchCriteria): void {
    this.pageNumber = 1;
    this.paginator.previousPage();
    if (criteria.name || criteria.country || criteria.city ||
       criteria.street || criteria.house) {
      this.searchCriteria = criteria;
      this.getSearchWarehouses();
    } else if (this.searchCriteria) {
      this.searchCriteria = null;
      this.size();
      this.getWarehouses();
    }
  }

  private log(message: string) {
    console.log('WarehouseListComponent: ' + message);
  }
}
