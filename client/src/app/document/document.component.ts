import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-document',
  templateUrl: './document.component.html',
  styleUrls: ['./document.component.sass']
})
export class DocumentComponent implements OnInit {

  selectedTab: number;

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

  ngOnInit() {
    this.selectTab();
  }

}
