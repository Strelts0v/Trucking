import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { Warehouse } from './warehouse';

@Injectable()
export class WarehouseService {

  constructor() {
  }

  getWarehouse(id: number): Observable<Warehouse> {
    if (id) {
      return of(WAREHOUSE_DATA.find(warehouse => warehouse.id === id));
    } else {
      return of(new Warehouse());
    }
  }

  getWarehouses(pageNumber: number, pageSize: number): Observable<Warehouse[]> {
    const offset = (pageNumber - 1) * pageSize;
    return of(WAREHOUSE_DATA.slice(offset, offset + pageSize));
  }

  size(): Observable<number> {
    return of(WAREHOUSE_DATA.length);
  }

}

const WAREHOUSE_DATA: Warehouse[] = [
  {
    id: 1,
    name: 'VOLVO 13-33 BY',
    country: 'Russia',
    city: 'Moscow',
    street: 'Leninskaya',
    house: 5,
    lat: 533553,
    lng: 533553
  }
];