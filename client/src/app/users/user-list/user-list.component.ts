import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { UserService } from './../index';

@Component({
  moduleId: module.id,
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.sass'],
})

export class UserListComponent implements OnInit, AfterViewInit {

  header = 'User list';
  displayedColumns = ['name', 'login', 'role'];
  dataSource = new MatTableDataSource<Element>(ELEMENT_DATA);

  constructor(userService: UserService) {
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
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
  login: string;
  role: string;
}

const ELEMENT_DATA: Element[] = [
  { name: 'Test name1', login: 'Test login', role: 'Test role'},
  { name: 'Test name2', login: 'Test login', role: 'Test role'},
  { name: 'Test name3', login: 'Test login', role: 'Test role'},
  { name: 'Test name4', login: 'Test login', role: 'Test role'},
  { name: 'Test name5', login: 'Test login', role: 'Test role'},
  { name: 'Test name6', login: 'Test login', role: 'Test role'},
  { name: 'Test name7', login: 'Test login', role: 'Test role'},
  { name: 'Test name8', login: 'Test login', role: 'Test role'},
  { name: 'Test name9', login: 'Test login', role: 'Test role'},
  { name: 'Test name10', login: 'Test login', role: 'Test role'},
  { name: 'Test name11', login: 'Test login', role: 'Test role'},
  { name: 'Test name12', login: 'Test login', role: 'Test role'},
  { name: 'Test name13', login: 'Test login', role: 'Test role'},
  { name: 'Test name14', login: 'Test login', role: 'Test role'},
  { name: 'Test name15', login: 'Test login', role: 'Test role'},
  { name: 'Test name16', login: 'Test login', role: 'Test role'},
  { name: 'Test name17', login: 'Test login', role: 'Test role'},
  { name: 'Test name18', login: 'Test login', role: 'Test role'},
  { name: 'Test name19', login: 'Test login', role: 'Test role'},
  { name: 'Test name20', login: 'Test login', role: 'Test role'},
  { name: 'Test name21', login: 'Test login', role: 'Test role'},
  { name: 'Test name22', login: 'Test login', role: 'Test role'},
  { name: 'Test name23', login: 'Test login', role: 'Test role'},
  { name: 'Test name24', login: 'Test login', role: 'Test role'},
];
