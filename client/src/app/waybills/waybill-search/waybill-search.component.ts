import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SearchService } from '../../main/search-bar/search.service';

@Component({
  selector: 'app-waybill-search',
  templateUrl: './waybill-search.component.html',
  styleUrls: ['./waybill-search.component.sass']
})
export class WaybillSearchComponent implements OnInit {

  wForm: FormGroup;

  constructor(private fb: FormBuilder,
              private searchService: SearchService) {
  }

  initForm() {
    this.wForm = this.fb.group({
      from: '',
      to: '',
      invoiceNumber: '',
      issueDate: ''
    });

    this.wForm.valueChanges.subscribe(value => this.searchService.addCriteria(value));
  }

  ngOnInit() {
    this.initForm();
  }

}
