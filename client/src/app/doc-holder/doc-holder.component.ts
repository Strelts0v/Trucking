import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

import { Invoice } from '../invoices/invoice';
import { InvoiceService } from '../invoices/invoice.service';
import { DocDataService } from './doc-data.service';

@Component({
  selector: 'app-document',
  templateUrl: './doc-holder.component.html',
  styleUrls: ['./doc-holder.component.sass']
})
export class DocHolderComponent implements OnInit {

  selectedTab: number;
  invoice: Invoice;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private invoiceService: InvoiceService,
              private invoiceDataService: DocDataService) {
  }

  selectTab() {
    switch (this.data.type) {
      case 'invoice':
        this.selectedTab = 0;
        break;
      case 'waybill':
        this.selectedTab = 1;
        break;
      case 'lossact':
        this.selectedTab = 3;
        break;
    }
  }

  ngOnInit() {
    this.selectTab();

    if (this.data.id) {
      this.invoiceService.getInvoice(this.data.id)
        .subscribe(invoice => {
          setTimeout(() => {
            this.invoiceDataService.changeInvoice(invoice);
          }, 1000);
        });
    }
  }

}
