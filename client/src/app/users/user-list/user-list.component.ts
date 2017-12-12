import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatDialog, MatPaginator, MatTableDataSource } from '@angular/material';
import { UserService } from './../user.service';
import { UserDetailComponent } from '../index';

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

  name: string;
  animal: string;

  constructor(
    userService: UserService,
    dialog: MatDialog) {
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

  openUserDetailDialog() {
    // const dialogRef = this.dialog.open(UserDetailComponent, {
    //   width: '250px',
    //   data: { name: this.name, animal: this.animal }
    // });

    // dialogRef.afterClosed().subscribe(result => {
    //   console.log('The dialog was closed');
    //   this.animal = result;
    // });
  }
}

export interface Element {
  name: string;
  login: string;
  role: string;
}

const ELEMENT_DATA: Element[] = [
  { name: 'Test name1', login: 'Test login1', role: 'SYSADMIN'},
  { name: 'Test name2', login: 'Test login2', role: 'DISPATCHER'},
  { name: 'Test name3', login: 'Test login3', role: 'ADMIN'},
  { name: 'Test name4', login: 'Test login4', role: 'MANAGER'},
  { name: 'Test name5', login: 'Test login5', role: 'SYSADMIN'},
  { name: 'Test name6', login: 'Test login6', role: 'SYSADMIN'},
  { name: 'Test name7', login: 'Test login7', role: 'DRIVER'},
  { name: 'Test name8', login: 'Test login8', role: 'MANAGER'},
  { name: 'Test name9', login: 'Test login9', role: 'DISPATCHER'},
  { name: 'Test name10', login: 'Test login10', role: 'OWNER'},
  { name: 'Test name11', login: 'Test login11', role: 'SYSADMIN'},
  { name: 'Test name12', login: 'Test login12', role: 'ADMIN'},
  { name: 'Test name13', login: 'Test login13', role: 'DRIVER'},
  { name: 'Test name14', login: 'Test login14', role: 'SYSADMIN'},
  { name: 'Test name15', login: 'Test login15', role: 'DRIVER'},
  { name: 'Test name16', login: 'Test login16', role: 'MANAGER'},
  { name: 'Test name17', login: 'Test login17', role: 'DISPATCHER'},
  { name: 'Test name18', login: 'Test login18', role: 'ADMIN'},
  { name: 'Test name19', login: 'Test login19', role: 'OWNER'},
  { name: 'Test name20', login: 'Test login20', role: 'SYSADMIN'},
  { name: 'Test name21', login: 'Test login21', role: 'OWNER'},
  { name: 'Test name22', login: 'Test login22', role: 'DISPATCHER'},
  { name: 'Test name23', login: 'Test login23', role: 'MANAGER'},
  { name: 'Test name24', login: 'Test login24', role: 'OWNER'},
];
