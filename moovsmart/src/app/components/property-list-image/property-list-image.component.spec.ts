import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PropertyListImageComponent} from './property-list-image.component';

describe('PropertyListImageComponent', () => {
  let component: PropertyListImageComponent;
  let fixture: ComponentFixture<PropertyListImageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PropertyListImageComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyListImageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
