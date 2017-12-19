import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs/Observable';
import { catchError, tap } from 'rxjs/operators';

import { Report } from './report';
import { of } from 'rxjs/observable/of';

@Injectable()
export class ReportService {

  private reportUrl = 'report';

  constructor(private http: HttpClient) {
  }

  getReport(startDate: string, endDate: string): Observable<Report> {
    const url = `${environment.apiUrl}/${this.reportUrl}`;
    return this.http.put<Report>(url, {startDate: startDate, endDate: endDate})
      .pipe(
        tap(_ => this.log(`fetched report start date = ${startDate}, end date = ${endDate}`)),
        catchError(this.handleError<Report>(`get report start date = ${startDate}, end date = ${endDate}`))
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
    console.log('ReportService: ' + message);
  }

}
