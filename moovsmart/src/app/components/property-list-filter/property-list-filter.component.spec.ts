import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PropertyListFilterComponent} from './property-list-filter.component';

describe('PropertyListFilterComponent', () => {
  let component: PropertyListFilterComponent;
  let fixture: ComponentFixture<PropertyListFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PropertyListFilterComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyListFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
