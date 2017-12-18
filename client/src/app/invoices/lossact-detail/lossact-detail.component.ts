import { Component, Input, OnInit } from '@angular/core';
import { animate, style, transition, trigger } from '@angular/animations';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Invoice, InvoiceStatus } from '../invoice';
import { Item } from '../../items/item';
import { InvoiceService } from '../invoice.service';
import { LossAct } from '../lossact';
import { DocHolderComponent } from '../../doc-holder/doc-holder.component';
import { MatDialog, MatDialogRef } from '@angular/material';
import { ConfirmDialogComponent } from '../../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-lossact',
  templateUrl: './lossact-detail.component.html',
  styleUrls: ['./lossact-detail.component.sass'],
  animations: [
    trigger('shrinkIn', [
      transition('void => *', [
        style({
          height: '0',
          opacity: 0
        }),
        animate('0.35s ease-out')
      ]),
      transition('* => void', [
        animate('0.25s ease-in', style({
          height: '0',
          opacity: 0
        }))
      ])
    ])
  ]
})
export class LossActDetailComponent implements OnInit {

  lForm: FormGroup;

  @Input() invoice: Invoice;

  edit: boolean;
  editAvailability: boolean;

  items: Item[] = [];

  constructor(private fb: FormBuilder,
              private invoiceService: InvoiceService,
              private parentDialogRef: MatDialogRef<DocHolderComponent>,
              private dialog: MatDialog) {
  }

  toggleEdit(): void {
    if (!this.edit) {
      this.edit = true;

      this.lossActs.controls.forEach(control => control.enable());
    }
  }

  submit(): void {
    const isValid = this.invoice.waybill.waybillCheckpoints.every(waybillCheckpoint => {
      return waybillCheckpoint.checked === true;
    });

    if (isValid) {
      this.invoice.lossActs = this.lForm.value.lossActs;
      this.invoiceService.createLossAct(this.invoice)
        .subscribe(invoice => this.parentDialogRef.close(invoice));
    } else {
      const dialogRef = this.dialog.open(ConfirmDialogComponent, {
        panelClass: 'app-doc-holder',
        data: {
          text: 'You must check all checkpoints before completing the transportation',
          trueAction: 'Ok',
          isAlert: true
        }
      });
    }
  }

  compareItem(item1: Item, item2: Item): boolean {
    return item1 && item2 && item1.id === item2.id;
  }

  shouldShowAddBtn(index: number): boolean {
    return index === (this.lossActs.controls.length - 1)
      && this.lossActs.controls[index].get('item').value
      && this.edit;
  }

  shouldShowRemoveBtn(): boolean {
    return this.lossActs.controls.length > 1
      && this.edit;
  }

  getItems(): void {
    this.invoice.consignments.forEach(consignment => this.items.push(consignment.item));
  }

  get lossActs(): FormArray {
    return this.lForm.controls.lossActs as FormArray;
  }

  addItem(lossAct?: LossAct): void {
    this.lossActs.push(
      this.fb.group({
        item: [{value: lossAct && lossAct.item, disabled: !this.edit}, Validators.required],
        amount: [{value: lossAct && lossAct.amount, disabled: !this.edit}, [Validators.required, Validators.min(1)]]
      })
    );
  }

  deleteItem(index: number) {
    this.lossActs.removeAt(index);
  }

  initForm(): void {
    this.lForm = this.fb.group({
      lossActs: this.fb.array([])
    });
  }

  ngOnInit(): void {
    this.initForm();

    this.getItems();

    if (this.invoice.lossActs.length) {
      this.invoice.lossActs.forEach(lossAct => this.addItem(lossAct));
    } else {
      this.addItem();
    }

    this.editAvailability = !(this.invoice.status === InvoiceStatus.DELIVERED || this.invoice.lossActs.length);
  }
}
