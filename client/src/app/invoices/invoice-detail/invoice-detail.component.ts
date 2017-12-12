import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MatIconRegistry } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { animate, group, state, style, transition, trigger } from '@angular/animations';

import { Item } from '../../items/item';
import { Consignment, ConsignmentStatus } from '../consignment';
import { Invoice } from '../invoice';
import { InvoiceService } from '../invoice.service';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';
import { User } from '../../users';

@Component({
  selector: 'app-invoice-detail',
  templateUrl: './invoice-detail.component.html',
  styleUrls: ['./invoice-detail.component.sass'],
  animations: [
    trigger('shrinkIn', [
      state('in', style({height: 'auto', transform: 'translateY(0)', opacity: 1})),
      transition('void => *', [
        style({height: 10, transform: 'translateY(-50px)', opacity: 0}),
        group([
          animate('0.3s ease', style({
            transform: 'translateY(0)',
            height: 'auto'
          })),
          animate('0.3s ease', style({
            opacity: 1
          }))
        ])
      ])
    ])
  ]
})
export class InvoiceDetailComponent implements OnInit {

  lForm: FormGroup;

  @Input() invoice: Invoice;
  user: User;

  edit: boolean;

  items: Item[] = [
    {id: 1, name: 'Cabbage', price: 0, unitCode: 'BA'},
    {id: 2, name: 'Wheat vodka', price: 0, unitCode: 'BOX'},
    {id: 3, name: 'Canned fish', price: 0, unitCode: 'BOX'},
    {id: 4, name: 'Tablet computer', price: 0, unitCode: 'PCS'},
    {id: 5, name: 'Laptop', price: 0, unitCode: 'PCS'},
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
              private dialog: MatDialog,
              private parentDialogRef: MatDialogRef<DocHolderComponent>) {

    iconRegistry.addSvgIcon(
      'package_variant',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icon/package-variant.svg'));
  }

  toggleEdit() {
    if (!this.edit) {
      this.edit = true;

      this.consignments.controls.forEach(control => control.enable());
      this.client.enable();
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
    this.invoice.client = this.lForm.value.client;
    this.invoice.consignments = this.lForm.value.consignments;
    console.log(JSON.stringify(this.invoice));
  }

  cancel() {
    console.log('Invoice canceled');
  }

  getConsignmentStatuses(): ConsignmentStatus[] {
    return Object.keys(ConsignmentStatus)
      .map(key => ConsignmentStatus[key]);
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

  get client(): FormControl {
    return this.lForm.controls.client as FormControl;
  }

  get consignments(): FormArray {
    return this.lForm.controls.consignments as FormArray;
  }

  addItem(consignment?: Consignment) {
    this.consignments.push(
      this.fb.group({
        item: [{value: consignment && consignment.item, disabled: !this.edit}, Validators.required],
        amount: [{value: consignment && consignment.amount, disabled: !this.edit}, [Validators.required, Validators.min(0)]],
        status: [{value: consignment && consignment.status, disabled: !this.edit}, Validators.required]
      })
    );
  }

  deleteItem(index: number) {
    this.consignments.removeAt(index);
  }

  initForm() {
    this.lForm = this.fb.group({
      client: [{value: '', disabled: true}, Validators.required],
      consignments: this.fb.array([])
    });
  }

  ngOnInit() {
    this.initForm();

    if (this.invoice.id) {
      console.log(this.invoice);
      this.invoice.consignments.forEach(consignment => this.addItem(consignment));
      this.client.setValue(this.invoice.client);
    } else {
      this.addItem();
      this.toggleEdit();
    }
  }

}
