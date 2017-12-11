import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Waybill, WaybillStatus } from './waybill';
import { of, } from 'rxjs/observable/of';

@Injectable()
export class WaybillService {

  constructor() {
  }

  getWaybill(id: number): Observable<Waybill> {
    if (id) {
      return of(WAYBILL_DATA.find(waybill => waybill.id === id));
    } else {
      return of(new Waybill());
    }
  }

  getWaybills(pageNumber: number, pageSize: number): Observable<Waybill[]> {
    const offset = (pageNumber - 1) * pageSize;
    return of(WAYBILL_DATA.slice(offset, offset + pageSize));
  }

  size(): Observable<number> {
    return of(WAYBILL_DATA.length);
  }

}

const WAYBILL_DATA: Waybill[] = [
  {
    id: 1,
    invoiceId: 1,
    departureDate: '9.12.2017',
    driver: {
      id: 1,
      firstName: 'Grace',
      lastName: 'Lastname',
      middleName: '',
      birthday: '',
      email: '',
      city: '',
      street: '',
      house: '',
      apartment: '',
      roles: [''],
      login: '',
      password: '',
      passport: ''
    },
    car: 'МАЗ 13-33 BY',
    from: 'Minsk',
    to: 'Moscow',
    waybillCheckpoints: [
      {
        checkpoint:
          {
            id: 1,
            name: 'New York',
            addition: 'Gag',
            place_id: 'fag1234',
            lat: 533553,
            lng: 533553
          },
        checked: true,
        checkDate: '5.12.2017'
      }
    ],
    status: WaybillStatus.STARTED,
    issueDate: '5.12.2017'
  }
];
