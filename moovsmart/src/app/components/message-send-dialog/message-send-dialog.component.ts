import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AccountDetailsPropertyModel} from "../../models/accountDetailsProperty.model";
import {MessageFormComponent} from "../message-form/message-form.component";

@Component({
  selector: 'app-message-send-dialog',
  templateUrl: './message-send-dialog.component.html',
  styleUrls: ['./message-send-dialog.component.css']
})

export class MessageSendDialogComponent implements OnInit {

  @ViewChild(MessageFormComponent, {static: false}) formComponent: MessageFormComponent;

  constructor(public dialogRef: MatDialogRef<MessageSendDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: {
                accountDetails: AccountDetailsPropertyModel,
                propertyId: number,
              }) {
  }

  ngOnInit() {
  }

  onCancel() {
    this.dialogRef.close();
  }

  onSubmit() {
    this.formComponent.submit();
  }

}
