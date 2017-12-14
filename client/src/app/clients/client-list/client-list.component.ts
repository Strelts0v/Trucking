import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog } from '@angular/material';
import { ClientService } from '../client.service';
import { Client } from '../client';
import { ClientDetailComponent } from './../client-detail/client-detail.component';

@Component({
  moduleId: module.id,
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.sass'],
})

export class ClientListComponent implements OnInit, AfterViewInit {

  header = 'Client list';
  displayedColumns = ['name'];
  dataSource = new MatTableDataSource<Client>(CLIENT_DATA);

  constructor(
    private clientService: ClientService,
    private dialog: MatDialog) {
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

  openClientDetail(name?: string, client?: Client) {
    let isEditable = false;
    if (client != null) {
      isEditable = true;
    }

    this.log(JSON.stringify(client));
    if (client == null) {
      client = new Client();
      this.log(JSON.stringify(client));
    }
    const dialogRef = this.dialog.open(ClientDetailComponent, {
      width: '300px',
      data: { client: client }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      client = <Client> result.client;
      this.log(JSON.stringify(client));

      let originalClient;
      if (isEditable) {
        this.log(`EDIT ${JSON.stringify(client)}`);
        originalClient = this.getClientByName(name);
        let clients = this.dataSource.data;
        clients = clients.filter(client => client !== originalClient);
        client.name = originalClient.name;
        clients.push(client);
      } else {
        const clients = this.dataSource.data;
        this.log(`ADD ${JSON.stringify(clients)}`);
        clients.push(client);
        this.dataSource.data = clients;
        // this.userService.addUser(user)
        //   .subscribe(user => this.dataSource.data.push(user));
      }
    });
  }

  private getClientByName(name: string): Client {
    const clients = this.dataSource.data;
    return clients.filter(client => client.name === name)[0];
  }

  private log(message: string) {
    console.log('UserListComponent: ' + message);
  }
}

const CLIENT_DATA: Client[] = [
  { id: 1, name: 'IBM' },
  { id: 2, name: 'ROS Innovations' },
  { id: 3, name: 'Amazon' },
  { id: 4, name: 'Google' },
];
