import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';

import { Item, ItemUnit } from '../entity/item';
import { Consignment, ConsignmentStatus } from '../entity/consignment';
import { Invoice } from '../entity/invoice';
import { InvoiceService } from '../invoice.service';

@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html',
  styleUrls: ['./invoice-detail.component.sass']
})
export class InvoiceDetailComponent implements OnInit {

  @Input() id: number;
  invoice: Invoice;

  cForm: FormGroup;

  items: Item[] = [
    {id: 1, name: 'Some product', price: 0, type: ItemUnit.KILOGRAM},
    {id: 33, name: 'Random stuff', price: 0, type: ItemUnit.LITER},
    {id: 7, name: 'RAB product', price: 0, type: ItemUnit.PIECE},
  ];

  clients: string[] = [
    'State Grid',
    'China National Petroleum',
    'Industrial & Commercial Bank of China',
    'CVS Health',
    'Amazon'
  ];

  constructor(private fb: FormBuilder,
              private invoiceService: InvoiceService,
              private iconRegistry: MatIconRegistry,
              private sanitizer: DomSanitizer) {

    iconRegistry.addSvgIcon(
      'package_variant',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icon/package-variant.svg'));
  }

  submit() {
    console.log('Invoice saved');
    this.invoice.consignments = this.cForm.value.consignments;
    console.log(JSON.stringify(this.invoice));
  }

  cancel() {
    console.log('Invoice canceled');
    // this.invoice = new Invoice();
  }

  get consignments(): FormArray {
    return this.cForm.get('consignments') as FormArray;
  }

  addItem(consignment: Consignment) {
    this.consignments.push(this.initItem(consignment));
  }

  addEmptyItem() {
    this.consignments.push(this.initItem());
  }

  deleteItem(index: number) {
    this.consignments.removeAt(index);
  }

  initItem(consignment?: Consignment): FormGroup {
    return this.fb.group({
      item: [consignment && consignment.item],
      amount: [consignment && consignment.amount],
      status: [consignment && consignment.status]
    });
  }

  displayItemNumber(item: Item): string {
    if (!item) {
      return '';
    }
    return String(item.id);
  }

  getConsignmentStatuses(): ConsignmentStatus[] {
    return Object.keys(ConsignmentStatus)
      .map(key => ConsignmentStatus[key]);
  }

  getInvoice() {
    this.invoiceService.getInvoice(this.id)
      .subscribe(invoice => {
        this.invoice = invoice;

        if (invoice.consignments.length) {
          this.invoice.consignments.forEach(consignment => this.addItem(consignment));
        } else {
          this.addEmptyItem();
        }
      });
  }

  compareItem(item1: Item, item2: Item) {
    return item1 && item2 && item1.id === item2.id;
  }

  ngOnInit() {
    this.cForm = this.fb.group({
      consignments: this.fb.array([])
    });

    this.getInvoice();
  }

}
