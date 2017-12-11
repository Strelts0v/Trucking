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
  pageSize = 3;
  length: number;
  pageEvent: PageEvent;

  constructor(private carSerivce: CarService,
              private dialog: MatDialog) {
  }

  getCars() {
    this.carSerivce.getCars()
      .subscribe(cars => this.dataSource.data = cars);
  }

  ngOnInit() {
    this.getCars();
  }

}
