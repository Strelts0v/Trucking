import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, tap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

import { Invoice } from './invoice';

@Injectable()
export class InvoiceService {

  private invoicesUrl = 'invoices';
  private invoiceUrl = 'invoice';

  constructor(private http: HttpClient) {
  }

  getInvoice(id: number): Observable<Invoice> {
    const url = `${environment.apiUrl}/${this.invoiceUrl}/${id}`;
    return this.http.get<Invoice>(url)
      .pipe(
        tap(_ => this.log(`fetched invoice id=${id}`)),
        catchError(this.handleError<Invoice>(`get invoice id=${id}`))
      );
  }

  getInvoices(pageNumber: number, pageSize: number): Observable<Invoice[]> {
    const url = `${environment.apiUrl}/${this.invoicesUrl}/${pageNumber}/${pageSize}`;
    return this.http.get<Invoice[]>(url)
      .pipe(
        tap(invoices => this.log('fetched invoices')),
        catchError(this.handleError('getInvoices', []))
      );
  }

  size(): Observable<number> {
    const url = `${environment.apiUrl}/${this.invoicesUrl}/size`;
    return this.http.get<number>(url)
      .pipe(
        tap(size => this.log(`fetched invoices size=${size}`)),
        catchError(this.handleError('invoices size', 0))
      );
  }

  registerInvoice(invoice: Invoice): Observable<Invoice> {
    const url = `${environment.apiUrl}/${this.invoiceUrl}/register`;
    return this.http.post<Invoice>(url, invoice)
      .pipe(
        tap((invoice: Invoice) => this.log(`registered invoice id=${invoice.id}`)),
        catchError(this.handleError<Invoice>('registerInvoice'))
      );
  }

  checkInvoice(invoice: Invoice): Observable<Invoice> {
    const url = `${environment.apiUrl}/${this.invoiceUrl}/check`;
    return this.http.put<Invoice>(url, invoice)
      .pipe(
        tap((invoice: Invoice) => this.log(`checked invoice id=${invoice.id}`)),
        catchError(this.handleError<Invoice>('checkInvoice'))
      );
  }

  completeInvoice(invoice: Invoice): Observable<Invoice> {
    const url = `${environment.apiUrl}/${this.invoiceUrl}/complete`;
    return this.http.put<Invoice>(url, invoice)
      .pipe(
        tap((invoice: Invoice) => this.log(`completed invoice id=${invoice.id}`)),
        catchError(this.handleError<Invoice>('completeInvoice'))
      );
  }

  createLossAct(invoice: Invoice): Observable<Invoice> {
    const url = `${environment.apiUrl}lossact/create`;
    return this.http.put<Invoice>(url, invoice)
      .pipe(
        tap((invoice: Invoice) => this.log(`created Act of Loss size=${invoice.lossActs.length}`)),
        catchError(this.handleError<Invoice>('createLossAct'))
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
    console.log('InvoiceService: ' + message);
  }
}
