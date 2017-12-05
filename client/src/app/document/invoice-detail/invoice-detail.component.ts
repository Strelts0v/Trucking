import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';

import { Item, ItemUnit } from '../entity/item';
import { Consignment, ConsignmentStatus } from '../entity/consignment';
import { Invoice } from '../entity/invoice';
import { InvoiceService } from '../invoice.service';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html',
  styleUrls: ['./invoice-detail.component.sass']
})
export class InvoiceDetailComponent implements OnInit {

  @Input() id: number;
  invoice: Invoice;

  edit: boolean;

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
              private sanitizer: DomSanitizer,
              private dialog: MatDialog) {

    iconRegistry.addSvgIcon(
      'package_variant',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icon/package-variant.svg'));
  }

  toggleEdit() {
    if (!this.edit) {
      this.edit = true;

      this.consignments.controls
        .forEach(control => this.edit ? control.enable() : control.disable());
    }
  }

  deleteInvoice() {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        text: 'Delete this consignment note?',
        trueAction: 'Delete',
        falseAction: 'Cancel'
      }
    });
  }

  submit() {
    console.log('Invoice saved');
    this.invoice.consignments = this.cForm.value.consignments;
    console.log(JSON.stringify(this.invoice));
  }

  cancel() {
    console.log('Invoice canceled');
  }

  get consignments(): FormArray {
    return this.cForm.get('consignments') as FormArray;
  }

  addItem(consignment?: Consignment) {
    this.consignments.push(
      this.fb.group({
        item: [{value: consignment && consignment.item, disabled: !this.edit}],
        amount: [{value: consignment && consignment.amount, disabled: !this.edit}],
        status: [{value: consignment && consignment.status, disabled: !this.edit}]
      })
    );
  }

  deleteItem(index: number) {
    this.consignments.removeAt(index);
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
          this.addItem();
          this.toggleEdit();
        }
      });
  }

  compareItem(item1: Item, item2: Item) {
    return item1 && item2 && item1.id === item2.id;
  }

  shouldShowAddBtn(index: number): boolean {
    return index === (this.consignments.controls.length - 1)
      && this.consignments.controls[index].get('item').value
      && this.edit;
  }

  shouldShowRemoveBtn(): boolean {
    return this.consignments.controls.length > 1
      && this.edit;
  }

  ngOnInit() {
    this.cForm = this.fb.group({
      consignments: this.fb.array([])
    });

    this.getInvoice();
  }

}
