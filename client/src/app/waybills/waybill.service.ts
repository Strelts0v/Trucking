import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of, } from 'rxjs/observable/of';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

import { Waybill } from './waybill';

@Injectable()
export class WaybillService {

  private waybillsUrl = 'waybills';
  private waybillUrl = 'waybill';

  constructor(private http: HttpClient) {
  }

  getWaybill(id: number): Observable<Waybill> {
    const url = `${environment.apiUrl}/${this.waybillUrl}/${id}`;
    return this.http.get<Waybill>(url)
      .pipe(
        tap(_ => this.log(`fetched waybill id=${id}`)),
        catchError(this.handleError<Waybill>(`get waybill id=${id}`))
      );
  }

  getWaybills(pageNumber: number, pageSize: number): Observable<Waybill[]> {
    const url = `${environment.apiUrl}/${this.waybillsUrl}/${pageNumber}/${pageSize}`;
    return this.http.get<Waybill[]>(url)
      .pipe(
        tap(waybills => this.log('fetched waybills')),
        catchError(this.handleError('getWaybills', []))
      );
  }

  size(): Observable<number> {
    const url = `${environment.apiUrl}/${this.waybillsUrl}/size`;
    return this.http.get<number>(url)
      .pipe(
        tap(size => this.log(`fetched waybills size=${size}`)),
        catchError(this.handleError('waybills size', 0))
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
    console.log('WaybillService: ' + message);
  }

}
