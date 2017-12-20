import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatTableDataSource, MatDialog, PageEvent } from '@angular/material';
import { ClientService } from './../client.service';
import { Client } from './../client';
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
  dataSource = new MatTableDataSource<Client>();
  pageNumber = 1;
  pageSize = 10;
  length: number;
  pageEvent: PageEvent;

  constructor(
    private clientService: ClientService,
    private dialog: MatDialog) {
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  ngOnInit(): void {
    this.size();
    this.getClients();
  }

  getClients() {
    this.clientService.getClients(this.pageNumber, this.pageSize)
      .subscribe(clients => this.dataSource.data = clients);
  }

  size() {
    this.clientService.getClientCount()
      .subscribe(length => this.length = length);
  }

  loadClients(event?: PageEvent) {
    this.pageNumber = event.pageIndex + 1;
    this.paginator.nextPage();
    this.pageSize = event.pageSize;
    this.getClients();
    this.size();
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  openClientDetail(id?: number, client?: Client) {
    let isEditable = false;
    client == null ? client = new Client() : isEditable = true;

    const dialogRef = this.dialog.open(ClientDetailComponent, {
      width: '320px',
      data: { client: client }
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      client = <Client> result.client;

      if (isEditable) {
        this.log(`EDIT ${JSON.stringify(client)}`);
        this.clientService.updateClient(client)
          .subscribe();
      } else {
        this.log(`ADD ${JSON.stringify(client)}`);
        this.clientService.addClient(client)
          .subscribe(client => {
            const clients = this.dataSource.data;
            this.log(`ADD ${JSON.stringify(client)}`);
            clients.push(client);
            this.dataSource.data = clients;
          });
      }
    });
  }

  private getClientById(id: number): Client {
    const clients = this.dataSource.data;
    return clients.filter(client => client.id === id)[0];
  }

  private log(message: string) {
    console.log('ClientListComponent: ' + message);
  }
}
