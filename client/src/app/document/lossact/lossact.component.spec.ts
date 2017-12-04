import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LossActComponent } from './lossact.component';

describe('LossActComponent', () => {
  let component: LossActComponent;
  let fixture: ComponentFixture<LossActComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LossActComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LossActComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
