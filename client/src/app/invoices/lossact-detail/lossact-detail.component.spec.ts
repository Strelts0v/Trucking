import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LossActDetailComponent } from './lossact-detail.component';

describe('LossActDetailComponent', () => {
  let component: LossActDetailComponent;
  let fixture: ComponentFixture<LossActDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LossActDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LossActDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
