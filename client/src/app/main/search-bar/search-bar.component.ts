import { Component, Input, OnDestroy } from '@angular/core';
import { animate, style, transition, trigger } from '@angular/animations';

import { SearchService } from './search.service';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.sass'],
  animations: [
    trigger('opacityOut', [
      transition('void => *', [
        style({
          opacity: 0,
          width: 0
        }),
        animate('0.2s 0.5s ease-out')
      ])
    ])
  ]
})
export class SearchBarComponent implements OnDestroy {

  @Input() path: string;

  constructor(private searchService: SearchService) {
  }

  ngOnDestroy(): void {
    this.searchService.addCriteria({});
  }

}
