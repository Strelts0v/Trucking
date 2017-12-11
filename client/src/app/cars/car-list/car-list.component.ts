import { Component, OnInit } from '@angular/core';
import { Car } from '../car';
import { MatDialog, MatTableDataSource, PageEvent } from '@angular/material';
import { CarService } from '../car.service';

@Component({
  selector: 'app-car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.sass']
})
export class CarListComponent implements OnInit {

  displayedColumns = ['id', 'name', 'consumption', 'number', 'type'];
  dataSource = new MatTableDataSource<Car>();
  pageNumber = 1;
  pageSize = 3;
  length: number;
  pageEvent: PageEvent;

  constructor(private carSerivce: CarService,
              private dialog: MatDialog) {
  }

  getCars() {
    this.carSerivce.getCars(this.pageNumber, this.pageSize)
      .subscribe(cars => this.dataSource.data = cars);
  }

  size() {
    this.carSerivce.size()
      .subscribe(length => this.length = length);
  }

  loadCars(event?: PageEvent) {
    this.pageNumber = event.pageIndex + 1;
    this.pageSize = event.pageSize;
    this.getCars();
    this.size();
  }


  ngOnInit() {
    this.getCars();
    this.size();
  }

}
