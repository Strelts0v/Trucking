import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

import { Item } from './item';

@Injectable()
export class ItemService {

  private itemsUri = 'items';

  constructor(private http: HttpClient) {
  }

  getItems(): Observable<Item[]> {
    const url = `${environment.apiUrl}/${this.itemsUri}/get_items`;
    return this.http.get<Item[]>(url)
      .pipe(
        tap(items => this.log('fetched items')),
        catchError(this.handleError('getItems', []))
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
    console.log('ItemService: ' + message);
  }

}
