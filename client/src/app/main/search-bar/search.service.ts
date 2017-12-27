import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class SearchService {

  private criteriaSource = new Subject();
  currentCriteria = this.criteriaSource.asObservable();

  constructor() {
  }

  addCriteria(criteria: any) {
    this.criteriaSource.next(criteria);
  }

}
