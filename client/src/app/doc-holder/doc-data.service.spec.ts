import { TestBed, inject } from '@angular/core/testing';

import { DocDataService } from './doc-data.service';

describe('DocDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DocDataService]
    });
  });

  it('should be created', inject([DocDataService], (service: DocDataService) => {
    expect(service).toBeTruthy();
  }));
});
