import { TestBed, inject } from '@angular/core/testing';

import { WaybillService } from './waybill.service';

describe('WaybillService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [WaybillService]
    });
  });

  it('should be created', inject([WaybillService], (service: WaybillService) => {
    expect(service).toBeTruthy();
  }));
});
