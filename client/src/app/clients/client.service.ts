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

  constructor (private http: HttpClient) {
  }

  getClients(page: number): Observable<Client[]> {
    const url = `${environment.apiUrl}/${this.clientsUrl}/get_clients?page=${page}`;
    return this.http.get<Client[]>(url)
      .pipe(
        tap(clients => this.log(`fetched clients`)),
        catchError(this.handleError('getClients', []))
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
