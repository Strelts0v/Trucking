import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { SearchService } from '../../main/search-bar/search.service';

@Component({
  selector: 'app-warehouse-search',
  templateUrl: './warehouse-search.component.html',
  styleUrls: ['./warehouse-search.component.sass']
})
export class WarehouseSearchComponent implements OnInit {

  iForm: FormGroup;

  constructor(private fb: FormBuilder,
              private searchService: SearchService) {
  }

  initForm() {
    this.iForm = this.fb.group({
      name: '',
      country: '',
      city: '',
      street: '',
      house: '',
    });

    this.iForm.valueChanges.subscribe(value => this.searchService.addCriteria(value));
  }

  ngOnInit() {
    this.initForm();
  }
}
