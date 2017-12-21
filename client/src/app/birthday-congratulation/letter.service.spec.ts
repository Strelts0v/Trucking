import { TestBed, inject } from '@angular/core/testing';

import { LetterService } from './letter.service';

describe('LetterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LetterService]
    });
  });

  it('should be created', inject([LetterService], (service: LetterService) => {
    expect(service).toBeTruthy();
  }));
});
