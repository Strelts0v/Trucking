import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WaybillDetailComponent } from './waybill-detail.component';

describe('WaybillDetailComponent', () => {
  let component: WaybillDetailComponent;
  let fixture: ComponentFixture<WaybillDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WaybillDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WaybillDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
