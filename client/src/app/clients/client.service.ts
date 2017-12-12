import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, tap } from 'rxjs/operators';

import { AuthService } from './../users/index';
import { Client } from './client';


@Injectable()
export class ClientService {

  private clientsUrl = 'http://localhost:8080/api/clients';

  constructor (private http: HttpClient) {
  }

  getClients(page: number): Observable<Client[]> {
    if (page <= 0) {
      return;
    }

    const httpOptions = {
      headers: new HttpHeaders({
        // add authorization header with jwt token
        'Authorization': `Bearer ${AuthService.token}`
      })
    };

    // get users from api
    return this.http.get<Client[]>(this.clientsUrl + `/get_clients?page=${page}`, httpOptions)
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
