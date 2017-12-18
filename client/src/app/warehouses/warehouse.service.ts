import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { catchError, tap } from 'rxjs/operators';
import { of } from 'rxjs/observable/of';
import { Warehouse } from './warehouse';
import { environment } from '../../environments/environment';

@Injectable()
export class WarehouseService {

  private warehousesUrl = 'warehouses';
  private getWarehousesUrl = 'get_warehouses';
  private getWarehouseCountUrl = 'count';
  private addWarehouseUrl = 'add_warehouse';
  private updateWarehouseUrl = 'update_warehouse';
  private deleteWarehouseUrl = 'delete_warehouse';

  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = `${environment.apiUrl}${this.warehousesUrl}/`;
  }

  getWarehouses(page: number, pageSize: number): Observable<Warehouse[]> {
    const url = `${this.apiUrl}${this.getWarehousesUrl}/${page}/${pageSize}`;
    this.log(url);
    return this.http.get<Warehouse[]>(url)
      .pipe(
        tap(warehouses => this.log(`fetched Warehouses ${JSON.stringify(warehouses)}`)),
        catchError(this.handleError('getWarehouses', []))
      );
  }

  getWarehouseCount(): Observable<number> {
    const url = `${this.apiUrl}${this.getWarehouseCountUrl}`;
    this.log(url);
    return this.http.get<number>(url)
      .pipe(
        tap(count => this.log(`fetched warehouse count ${count}`)),
        catchError(this.handleError('getClientCount', 0))
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
