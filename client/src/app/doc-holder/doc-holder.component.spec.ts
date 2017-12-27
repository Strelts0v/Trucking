import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocHolderComponent } from './doc-holder.component';

describe('DocHolderComponent', () => {
  let component: DocHolderComponent;
  let fixture: ComponentFixture<DocHolderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocHolderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocHolderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
