import { Component, Input, OnInit } from '@angular/core';

import { Invoice } from '../invoice';

@Component({
  selector: 'app-lossact',
  templateUrl: './lossact-detail.component.html',
  styleUrls: ['./lossact-detail.component.sass']
})
export class LossActDetailComponent implements OnInit {

  @Input() invoice: Invoice;

  constructor() {
  }

  ngOnInit() {
  }

}
