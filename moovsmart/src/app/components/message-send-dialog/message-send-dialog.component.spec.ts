import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MessageSendDialogComponent} from './message-send-dialog.component';

describe('PopupComponent', () => {
  let component: MessageSendDialogComponent;
  let fixture: ComponentFixture<MessageSendDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MessageSendDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessageSendDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
