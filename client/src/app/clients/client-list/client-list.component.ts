import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { ClientService } from '../client.service';

@Component({
  moduleId: module.id,
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.sass'],
})

export class ClientListComponent implements OnInit, AfterViewInit {

  header = 'User list';
  displayedColumns = ['name'];
  dataSource = new MatTableDataSource<Element>(ELEMENT_DATA);

  constructor(clientService: ClientService) {
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {

  }

  /**
   * Set the paginator after the view init since this component will
   * be able to query its view for the initialized paginator.
   */
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }
}

export interface Element {
  name: string;
}

const ELEMENT_DATA: Element[] = [
  { name: 'Test name1' },
  { name: 'Test name2' },
  { name: 'Test name3' },
  { name: 'Test name4' },
  { name: 'Test name5' },
  { name: 'Test name6' },
  { name: 'Test name7' },
  { name: 'Test name8' },
  { name: 'Test name9' },
  { name: 'Test name10' },
];
