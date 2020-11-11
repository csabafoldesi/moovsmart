import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PropertyListGridComponent} from './property-list-grid.component';

describe('PropertyListGridComponent', () => {
  let component: PropertyListGridComponent;
  let fixture: ComponentFixture<PropertyListGridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PropertyListGridComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyListGridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
