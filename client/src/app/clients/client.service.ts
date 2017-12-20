import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';

import { Client } from './client';

@Injectable()
export class ClientService {

  private clientsUrl = 'clients';
  private getClientsUrl = 'get_clients';
  private getClientCountUrl = 'count';
  private addClientUrl = 'add_client';
  private updateClientUrl = 'update_client';
  private deleteClientUrl = 'delete_client';

  private apiUrl: string;

  constructor (private http: HttpClient) {
    this.apiUrl = `${environment.apiUrl}${this.clientsUrl}/`;
  }

  getClients(page: number, pageSize: number): Observable<Client[]> {
    const url = `${this.apiUrl}${this.getClientsUrl}/${page}/${pageSize}`;
    this.log(url);
    return this.http.get<Client[]>(url)
      .pipe(
        tap(clients => this.log(`fetched clients ${JSON.stringify(clients)}`)),
        catchError(this.handleError('getClients', []))
      );
  }

  getClientCount(): Observable<number> {
    const url = `${this.apiUrl}${this.getClientCountUrl}`;
    this.log(url);
    return this.http.get<number>(url)
      .pipe(
        tap(count => this.log(`fetched client count ${count}`)),
        catchError(this.handleError('getClientCount', 0))
      );
  }

  addClient(client: Client): Observable<Client> {
    const url = `${this.apiUrl}${this.addClientUrl}`;
    this.log(url);
    return this.http.post<Client>(url, client)
    .pipe(
      tap((client: Client) => this.log(`added client id=${client.id}`)),
      catchError(this.handleError<Client>('addClient'))
    );
  }

  updateClient(client: Client): Observable<Client> {
    const url = `${this.apiUrl}${this.updateClientUrl}`;
    this.log(url);
    return this.http.put<Client>(url, client)
      .pipe(
        tap((client: Client) => this.log(`client was successfully updated: ${JSON.stringify(client)}`)),
        catchError(this.handleError<Client>('updateClient'))
      );
  }

  // deleteClient(client: Client): Observable<Client> {
  //   const url = `${this.apiUrl}${this.deleteClientUrl}`;
  //   this.log(url);
  //   return this.http.post<Client>(url, client)
  //     .pipe(
  //       tap((client: Client) => this.log(`client with id ${client.id} was successfully deleted.`)),
  //       catchError(this.handleError<Client>('deleteClient'))
  //     );
  // }

  deleteClient(client: Client): Observable<void> {
    const url = `${this.apiUrl}${this.deleteClientUrl}`;
    this.log(url);
    return this.http.post<void>(url, client)
      .pipe(
        tap(_ => this.log(`client with id ${client.id} was successfully deleted.`)),
        catchError(this.handleError<void>('deleteClient'))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log('ClientService: ' + message);
  }
}
