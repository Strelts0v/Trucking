import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WaybillSearchComponent } from './waybill-search.component';

describe('WaybillSearchComponent', () => {
  let component: WaybillSearchComponent;
  let fixture: ComponentFixture<WaybillSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WaybillSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WaybillSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
