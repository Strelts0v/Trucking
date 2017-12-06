import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InvoiceListComponent } from '../invoices/invoice-list/invoice-list.component';

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [InvoiceListComponent]
})
export class DocHolderModule { }
