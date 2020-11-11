import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {MessageService} from "../../services/message.service";
import {Router} from "@angular/router";
import {validationHandler} from "../../utils/validationHandler";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AccountDetailsMessageModel} from "../../models/accountDetailsMessage.model";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

@Component({
  selector: 'app-message-form',
  templateUrl: './message-form.component.html',
  styleUrls: ['./message-form.component.css']
})
export class MessageFormComponent implements OnInit {

  @Input() accountDetails: AccountDetailsMessageModel;
  @Input() propertyId: number;
  @Output() dialogCloseEmitter: EventEmitter<any> = new EventEmitter();

  messageForm = this.formBuilder.group({
    toId: [''],
    propertyId: [''],
    content: [''],
  });

  constructor(private formBuilder: FormBuilder,
              private messageService: MessageService,
              private router: Router,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.messageForm.patchValue({
      toId: this.accountDetails.id,
      propertyId: this.propertyId,
    });
  }

  submit() {
    const data = {...this.messageForm.value};
    this.messageService.sendMessage(data).subscribe(
      () => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'MESSAGE_SENT'},
          duration: 3500,
          panelClass: ['green-snackbar'],
        });
        /*const message = {message: 'Message has been sent.', action: 'OK'};
        this._snackBar.open(message.message, message.action, {
          duration: 3500,
          panelClass: ['green-snackbar']
        });*/
        this.messageService.refreshListSubject.next();
        this.dialogCloseEmitter.emit();
      },
      (error) => {
        validationHandler(error, this.messageForm);
      },
      () => {
        this.messageForm.patchValue({
          content: ''
        });
      }
    );
  }
}
