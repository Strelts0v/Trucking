import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Invoice, InvoiceStatus } from './invoice';
import { ConsignmentStatus } from './consignment';
import { ItemUnit } from '../item';

@Injectable()
export class InvoiceService {

  constructor() {
  }

  getInvoice(id: number): Observable<Invoice> {
    if (id) {
      return of(INVOICE_DATA.find(invoice => invoice.id === id));
    } else {
      return of(new Invoice());
    }
  }

  getInvoices(pageNumber: number, pageSize: number): Observable<Invoice[]> {
    const offset = (pageNumber - 1) * pageSize;
    return of(INVOICE_DATA.slice(offset, offset + pageSize));
  }

  size(): Observable<number> {
    return of(INVOICE_DATA.length);
  }

}

const INVOICE_DATA: Invoice[] = [
  {
    id: 1,
    issueDate: '1.12.2017',
    consignments: [
      {
        item: {id: 33, name: 'Random stuff', price: 0, type: ItemUnit.LITER},
        amount: 55,
        status: ConsignmentStatus.CHECKED
      },
      {
        item: {id: 33, name: 'Some product', price: 0, type: ItemUnit.KILOGRAM},
        amount: 55,
        status: ConsignmentStatus.REGISTERED
      }],
    status: InvoiceStatus.CHECKED,
    checkDate: '2.12.2017',
    creator: {
      firstName: 'Oliver',
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
    inspector: {
      firstName: 'Oliver',
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
    client: 'State Grid'
  },
  {
    id: 2,
    issueDate: '3.12.2017',
    consignments: [{
      item: {id: 33, name: 'Some product', price: 0, type: ItemUnit.KILOGRAM},
      amount: 55,
      status: ConsignmentStatus.REGISTERED
    }],
    status: null,
    checkDate: '4.12.2017',
    creator: null,
    inspector: null,
    client: 'China National Petroleum'
  },
  {
    id: 3,
    issueDate: '5.12.2017',
    consignments: [{
      item: {id: 33, name: 'Random stuff', price: 0, type: ItemUnit.PIECE},
      amount: 55,
      status: ConsignmentStatus.LOST
    }],
    status: InvoiceStatus.ISSUED,
    checkDate: '6.12.2017',
    creator: {
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
    inspector: null,
    client: 'Industrial & Commercial Bank of China'
  },
  {
    id: 7,
    issueDate: '7.12.2017',
    consignments: [{
      item: {id: 33, name: 'Random stuff', price: 0, type: ItemUnit.LITER},
      amount: 55,
      status: ConsignmentStatus.CHECKED
    }],
    status: InvoiceStatus.CHECKED,
    checkDate: '8.12.2017',
    creator: {
      firstName: 'Adalyn',
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
    inspector: {
      firstName: 'Jacob',
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
    client: 'CVS Health'
  },
  {
    id: 12,
    issueDate: '9.12.2017',
    consignments: [{
      item: {id: 33, name: 'Random stuff', price: 0, type: ItemUnit.LITER},
      amount: 55,
      status: ConsignmentStatus.CHECKED
    }],
    status: null,
    checkDate: '10.12.2017',
    creator: {
      firstName: 'Eliana',
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
    inspector: {
      firstName: 'Emily',
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
    client: 'Amazon'
  }
];
