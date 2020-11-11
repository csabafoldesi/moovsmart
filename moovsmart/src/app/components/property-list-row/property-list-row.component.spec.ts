import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PropertyListRowComponent} from './property-list-row.component';

describe('PropertyListRowComponent', () => {
  let component: PropertyListRowComponent;
  let fixture: ComponentFixture<PropertyListRowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PropertyListRowComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyListRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
