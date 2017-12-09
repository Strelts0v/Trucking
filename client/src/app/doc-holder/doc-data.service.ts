import { Injectable } from '@angular/core';

import { Invoice } from '../invoices/invoice';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class DocDataService {

  private invoiceSource = new Subject<Invoice>();
  currentInvoice = this.invoiceSource.asObservable();

  constructor() {
  }

  changeInvoice(invoice: Invoice) {
    this.invoiceSource.next(invoice);
  }

}
