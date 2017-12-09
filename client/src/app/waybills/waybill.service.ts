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
    departureDate: '6.12.2017',
    driver: {
      firstName: 'Oliver',
      lastName: 'Lastname',
      middleName: '',
      birthday: '',
      email: '',
      city: '',
      street: '',
      house: '',
      apartment: '',
      roles: '',
      login: '',
      password: '',
      passport: ''
    },
    car: 'Lexus RX 350',
    from: 'Minsk',
    to: 'Grodno',
    waybillCheckpoints: [
      {
        checkpoint: {name: 'Lida', lat: '53.903044', lng: '25.2589413'},
        checkDate: ''
      },
      {
        checkpoint: {name: 'Skydzyel', lat: '53.582869', lng: '24.2148433'},
        checkDate: ''
      }
    ],
    status: WaybillStatus.STARTED,
    issueDate: '6.12.2017'
  }
];
