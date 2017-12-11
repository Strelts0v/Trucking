import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BithdayCongratulationComponent } from './bithday-congratulation.component';

describe('BithdayCongratulationComponent', () => {
  let component: BithdayCongratulationComponent;
  let fixture: ComponentFixture<BithdayCongratulationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BithdayCongratulationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BithdayCongratulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
