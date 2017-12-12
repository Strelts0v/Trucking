import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

import { Invoice, InvoiceStatus } from '../invoices/invoice';
import { InvoiceService } from '../invoices/invoice.service';

@Component({
  selector: 'app-doc-holder',
  templateUrl: './doc-holder.component.html',
  styleUrls: ['./doc-holder.component.sass']
})
export class DocHolderComponent implements OnInit {

  selectedTab: number;
  isInvoiceLoaded: boolean;
  isWaybillLoaded: boolean;
  isLossActLoaded: boolean;
  isTabDisabled = true;

  invoice: Invoice;

  constructor(@Inject(MAT_DIALOG_DATA) private data: any,
              private invoiceService: InvoiceService) {
  }

  selectTab() {
    switch (this.data.type) {
      case 'invoice':
        this.selectedTab = 0;
        break;
      case 'waybill':
        this.selectedTab = 1;
        break;
    }
  }

  loadData() {
    if (this.data.id) {
      this.invoiceService.getInvoice(this.data.id)
        .subscribe(invoice => {
          this.invoice = invoice;

          this.isInvoiceLoaded = true;
          if (this.invoice.waybill) {
            this.isWaybillLoaded = true;
          }
          if (this.invoice.lossActs.length) {
            this.isLossActLoaded = true;
          }

          this.isTabDisabled = this.invoice.status !== (InvoiceStatus.CHECKED || InvoiceStatus.DELIVERED);
        });
    } else {
      this.invoice = new Invoice();
      this.isInvoiceLoaded = true;
    }
  }

  ngOnInit() {
    this.selectTab();
    this.loadData();
  }
}

@Component({
  selector: 'app-doc-holder-placeholder',
  templateUrl: 'doc-holder-placeholder.html'
})
export class DocHolderPlaceholderComponent {
}
