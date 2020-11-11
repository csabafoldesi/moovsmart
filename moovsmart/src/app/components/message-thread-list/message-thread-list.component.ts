import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {AccountDetailsMessageModel} from "../../models/accountDetailsMessage.model";
import {MessageListItemModel} from "../../models/messageListItem.model";
import {MessageService} from "../../services/message.service";
import {AccountService} from "../../services/account.service";
import {AuthenticatedAccountModel} from "../../models/authenticatedAccount.model";
import {PropertyDetailsMessageModel} from "../../models/propertyDetailsMessage.model";

@Component({
  selector: 'app-message-thread-list',
  templateUrl: './message-thread-list.component.html',
  styleUrls: ['./message-thread-list.component.css']
})
export class MessageThreadListComponent implements OnInit {

  messageList: Array<MessageListItemModel> = [];
  accountInfo: AuthenticatedAccountModel;
  @Input() otherPersonAccountDetails: AccountDetailsMessageModel;
  @Input() propertyDetails: PropertyDetailsMessageModel;

  @ViewChild('scrollMe', {static: false}) private myScrollContainer: ElementRef;

  constructor(private messageService: MessageService,
              private accountService: AccountService) {
  }

  ngOnInit() {
    this.getAccountInfo();
    this.collectThreadMessages();
  }

  collectThreadMessages(): void {
    this.messageService.collectThreadMessages(
      this.otherPersonAccountDetails.email, this.propertyDetails.id).subscribe(
      (response) => {
        this.messageList = response;
      }
    );
  }

  private getAccountInfo(): void {
    this.accountService.accountInfo().subscribe(
      (response) => {
        this.accountInfo = response;
      }
    );
  }

}
