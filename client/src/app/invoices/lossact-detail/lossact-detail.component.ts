import { Component, Input, OnInit } from '@angular/core';
import { animate, group, state, style, transition, trigger } from '@angular/animations';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

import { Invoice } from '../invoice';
import { Item } from '../../items/item';
import { InvoiceService } from '../invoice.service';
import { LossAct } from '../lossact';

@Component({
  selector: 'app-lossact',
  templateUrl: './lossact-detail.component.html',
  styleUrls: ['./lossact-detail.component.sass'],
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
export class LossActDetailComponent implements OnInit {

  lForm: FormGroup;

  @Input() invoice: Invoice;

  edit: boolean;

  items: Item[] = [
    {id: 1, name: 'Cabbage', price: 0, unitCode: 'BA'},
    {id: 2, name: 'Wheat vodka', price: 0, unitCode: 'BOX'},
    {id: 3, name: 'Canned fish', price: 0, unitCode: 'BOX'},
    {id: 4, name: 'Tablet computer', price: 0, unitCode: 'PCS'},
    {id: 5, name: 'Laptop', price: 0, unitCode: 'PCS'},
  ];

  constructor(private fb: FormBuilder,
              private invoiceService: InvoiceService) {
  }

  get lossActs(): FormArray {
    return this.lForm.controls.lossActs as FormArray;
  }

  addItem(lossAct?: LossAct): void {
    this.lossActs.push(
      this.fb.group({
        item: [{value: lossAct && lossAct.item, disabled: !this.edit}],
        amount: [{value: lossAct && lossAct.amount, disabled: !this.edit}, Validators.min(0)]
      })
    );
  }

  initForm(): void {
    this.lForm = this.fb.group({
      lossActs: this.fb.array([])
    });
  }

  ngOnInit() {
    this.initForm();

    if (this.invoice.id) {
      this.invoice.lossActs.forEach(lossAct => this.addItem(lossAct));
    }
  }
}
