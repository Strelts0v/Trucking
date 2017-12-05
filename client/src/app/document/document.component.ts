import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MAT_DIALOG_DATA, MatTabGroup } from '@angular/material';

import { WaybillComponent } from './waybill/waybill.component';
import { LossActComponent } from './lossact/lossact.component';
import { InvoiceDetailComponent } from './invoice-detail/invoice-detail.component';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.sass']
})
export class DocumentComponent implements OnInit {

  selectedTab: number;

  @ViewChild('invoice')
  private invoice: InvoiceDetailComponent;

  @ViewChild('waybill')
  private waybill: WaybillComponent;

  @ViewChild('lossact')
  private lossact: LossActComponent;

  @ViewChild('tabs')
  private tabs: MatTabGroup;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
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

  cancel() {
    switch (this.tabs.selectedIndex) {
      case 0:
        this.invoice.cancel();
        break;
    }
  }

  submit() {
    switch (this.tabs.selectedIndex) {
      case 0:
        this.invoice.submit();
        break;
    }
  }

  ngOnInit() {
    this.selectTab();
  }

}
