import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BirthdayCongratulationComponent } from './birthday-congratulation.component';

describe('BirthdayCongratulationComponent', () => {
  let component: BirthdayCongratulationComponent;
  let fixture: ComponentFixture<BirthdayCongratulationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BirthdayCongratulationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BirthdayCongratulationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
