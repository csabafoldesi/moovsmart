import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BatchUploadResultComponent} from './batch-upload-result.component';

describe('BatchUploadResultComponent', () => {
  let component: BatchUploadResultComponent;
  let fixture: ComponentFixture<BatchUploadResultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BatchUploadResultComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BatchUploadResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
