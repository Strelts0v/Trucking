import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { SearchService } from '../../main/search-bar/search.service';
import { InvoiceStatus } from '../invoice';

@Component({
  selector: 'app-invoice-search',
  templateUrl: './invoice-search.component.html',
  styleUrls: ['./invoice-search.component.sass']
})
export class InvoiceSearchComponent implements OnInit {

  iForm: FormGroup;

  invoiceStatuses: InvoiceStatus[];

  constructor(private fb: FormBuilder,
              private searchService: SearchService) {
  }

  initForm() {
    this.iForm = this.fb.group({
      issueDate: '',
      checkDate: '',
      status: '',
      inspector: ''
    });

    this.iForm.valueChanges.subscribe(value => this.searchService.addCriteria(value));
  }

  ngOnInit() {
    this.invoiceStatuses = Object.keys(InvoiceStatus).map(key => InvoiceStatus[key]);

    this.initForm();
  }

}
