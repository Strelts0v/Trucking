import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatTableDataSource, PageEvent } from '@angular/material';
import { UserService } from '../user.service';
import { UserDetailComponent } from '../user-detail/user-detail.component';
import { MatDialog, MatDialogRef } from '@angular/material';
import { User } from '../user';

@Component({
  moduleId: module.id,
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.sass'],
})

export class UserListComponent implements OnInit, AfterViewInit {

  displayedColumns = ['name', 'login', 'available_roles'];
  dataSource = new MatTableDataSource<User>();
  pageNumber = 1;
  pageSize = 10;
  length: number;
  pageEvent: PageEvent;

  constructor(
    private userService: UserService,
    private dialog: MatDialog) {
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.size();
    this.getUsers();
  }

  getUsers() {
    this.userService.getUsers(this.pageNumber, this.pageSize)
      .subscribe(users => this.dataSource.data = users);
  }

  size() {
    this.userService.size()
      .subscribe(length => this.length = length);
  }

  loadUsers(event?: PageEvent) {
    this.pageNumber = event.pageIndex + 1;
    this.paginator.nextPage();
    this.pageSize = event.pageSize;
    this.getUsers();
    this.size();
  }

  /**
   * Set the paginator after the view init since this component will
   * be able to query its view for the initialized paginator.
   */
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  openUserDetail(id?: number, user?: User) {
    let isEditable = false;
    if (user != null) {
      isEditable = true;
    }

    this.log(JSON.stringify(user));
    if (user == null) {
      user = new User();
      this.log(JSON.stringify(user));
    }
    const dialogRef = this.dialog.open(UserDetailComponent, {
      width: '600px',
      data: { user: user }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      user = <User> result.user;
      this.log(JSON.stringify(user));

      let originalUser;
      if (isEditable) {
        this.log(`EDIT ${JSON.stringify(user)}`);
        originalUser = this.getUserById(id);
        let users = this.dataSource.data;
        users = users.filter(user => user !== originalUser);
        user.id = originalUser.id;
        users.push(user);
      } else {
        const users = this.dataSource.data;
        this.log(`ADD ${JSON.stringify(user)}`);
        users.push(user);
        this.dataSource.data = users;
        // this.userService.addUser(user)
        //   .subscribe(user => this.dataSource.data.push(user));
      }
    });
  }

  private getUserById(id: number): User {
    const users = this.dataSource.data;
    return users.filter(user => user.id = id)[0];
  }

  private log(message: string) {
    console.log('UserListComponent: ' + message);
  }
}
