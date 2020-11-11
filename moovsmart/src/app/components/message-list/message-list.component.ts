import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {MessageListItemModel} from "../../models/messageListItem.model";
import {MessageService} from "../../services/message.service";
import {PageEvent} from "@angular/material/paginator";
import {FormBuilder} from "@angular/forms";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDialogComponent} from "../confirm-dialog/confirm-dialog.component";
import {MessageSendDialogComponent} from "../message-send-dialog/message-send-dialog.component";
import {AccountDetailsMessageModel} from "../../models/accountDetailsMessage.model";
import {MessageListFilterModel} from "../../models/messageListFilter.model";
import {ActivatedRoute, Router} from "@angular/router";
import {Sort} from "@angular/material/sort";
import {PropertyDetailsMessageInitDataModel} from "../../models/propertyDetailsMessageInitData.model";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

interface SortOptions {
  value: string;
}

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css'],
})
export class MessageListComponent implements OnInit, OnDestroy {

  pageLength: number;
  page: number = 0;
  pageSizeOptions: number[] = [5, 10, 25, 100];
  pageSize: number = this.pageSizeOptions[1];
  sortOptions: SortOptions[] = [
    {value: 'id'},
    {value: 'id.desc'},
    {value: 'timestampCreated'},
    {value: 'timestampCreated.desc'},
    {value: 'from.fullName'},
    {value: 'from.fullName.desc'},
    {value: 'to.fullName'},
    {value: 'to.fullName.desc'},
    {value: 'property.name'},
    {value: 'property.name.desc'},
  ];
  sortBy: string = this.sortOptions[1].value;
  searchParameters: MessageListFilterModel;

  @Input() isIncoming: boolean;
  messageList: Array<MessageListItemModel> = [];
  propertiesList: Array<PropertyDetailsMessageInitDataModel> = [];
  expandedPanels = new Set();
  openedMessageThreads = new Set();
  refreshListSubscription;

  listForm = this.formBuilder.group({
    incoming: [''],
    propertyId: [0],
    startDate: [''],
    endDate: [''],
  });

  isInvalidStartDate = false;
  isInvalidEndDate = false;
  currentDate: Date;

  constructor(private formBuilder: FormBuilder,
              private messageService: MessageService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private _snackBar: MatSnackBar,
              private dialog: MatDialog) {
    this.refreshListSubscription = this.messageService.refreshListSubject.subscribe(
      () => {
        this.ngOnInit();
      }
    );
  }

  ngOnInit() {
    this.listForm.patchValue({
      incoming: this.isIncoming,
    });
    this.getInitSearchParameters();
    this.getActiveMessagesOnePage();
    this.getPropertiesForMessages();
    this.getCurrentDate();
  }

  ngOnDestroy(): void {
    if (this.refreshListSubscription) {
      this.refreshListSubscription.unsubscribe();
    }
  }

  private getInitSearchParameters() {
    this.searchParameters = this.listForm.value;
  }

  private getActiveMessagesOnePage() {
    this.messageService.getMessages(this.page, this.pageSize, this.sortBy, this.searchParameters).subscribe(
      messageListItems => {
        this.pageLength = messageListItems.totalItems;
        this.messageList = messageListItems.messageListItems;
      }
    );
  }

  private getPropertiesForMessages() {
    this.messageService.getPropertiesForMessages(this.isIncoming).subscribe(
      (response) => {
        this.propertiesList = response;
      }
    );
  }

  private getCurrentDate() {
    this.messageService.getCurrentDate().subscribe(
      (response) => {
        this.currentDate = response;
      }
    );
  }

  submitFilter() {
    this.page = 0;
    this.searchParameters = this.listForm.value;
    this.getActiveMessagesOnePage();
    this.getPropertiesForMessages();
    this.getCurrentDate();
  }

  checkDates() {
    this.isInvalidEndDate = false;
    if (this.listForm.value.startDate !== null && this.listForm.value.startDate !== ''
      && (this.listForm.value.startDate > this.currentDate)) {
      this.listForm.patchValue({
        startDate: this.currentDate,
      });
    }
    if (this.listForm.value.endDate !== null && this.listForm.value.endDate !== ''
      && (this.listForm.value.endDate > this.currentDate)) {
      this.listForm.patchValue({
        endDate: this.currentDate,
      });
    }
    if (this.listForm.value.startDate !== null && this.listForm.value.startDate !== ''
      && this.listForm.value.endDate !== null && this.listForm.value.endDate !== ''
      && (this.listForm.value.endDate < this.listForm.value.startDate)) {
      this.isInvalidEndDate = true;
    }
  }

  refreshMessageList() {
    this.page = 0;
    this.listForm.patchValue({
      propertyId: 0,
      startDate: '',
      endDate: '',
    });
    this.ngOnInit();
  }

  onPageChanged($event: PageEvent) {
    this.page = $event.pageIndex;
    this.pageSize = $event.pageSize;
    this.getActiveMessagesOnePage();
  }

  sortData($event: Sort) {
    if (!$event.active || $event.direction === '') {
      this.sortBy = this.sortOptions[1].value;
    } else if ($event.direction === 'asc') {
      if ($event.active.toString() === 'date') {
        this.sortBy = this.sortOptions[2].value;
      } else if ($event.active.toString() === 'name' && this.isIncoming) {
        this.sortBy = this.sortOptions[4].value;
      } else if ($event.active.toString() === 'name' && !this.isIncoming) {
        this.sortBy = this.sortOptions[6].value;
      } else if ($event.active.toString() === 'property') {
        this.sortBy = this.sortOptions[8].value;
      }
    } else if ($event.direction === 'desc') {
      if ($event.active.toString() === 'date') {
        this.sortBy = this.sortOptions[3].value;
      } else if ($event.active.toString() === 'name' && this.isIncoming) {
        this.sortBy = this.sortOptions[5].value;
      } else if ($event.active.toString() === 'name' && !this.isIncoming) {
        this.sortBy = this.sortOptions[7].value;
      } else if ($event.active.toString() === 'property') {
        this.sortBy = this.sortOptions[9].value;
      }
    }
    this.getActiveMessagesOnePage();
  }

  checkExpanded(id: number) {
    for (var item of Array.from(this.expandedPanels.values())) {
      if (item === id) {
        return true;
      }
    }
    return false;
  }

  openPanel(id: number) {
    this.expandedPanels.add(id);
  }

  closePanel(id: number) {
    this.expandedPanels.delete(id);
  }

  readMessage(id: number, unRead: boolean) {
    if (this.isIncoming && unRead) {
      this.messageService.readMessage(id).subscribe(
        () => {
        }
      );
    }
  }

  propertyDetails(id: number) {

    this.router.navigate(['property-details', id]);
  }

  reply(accountDetailsMessageModel: AccountDetailsMessageModel, propertyId: number) {
    this.dialog.open(MessageSendDialogComponent, {
      data: {
        accountDetails: accountDetailsMessageModel,
        propertyId: propertyId,
      }
    });
  }

  toggleMessageThread(id: number) {
    let isFound = false;
    for (var item of Array.from(this.openedMessageThreads.values())) {
      if (item === id) {
        isFound = true;
      }
    }
    if (isFound) {
      this.openedMessageThreads.delete(id);
    } else {
      this.openedMessageThreads.add(id);
    }
  }

  checkOpenedThread(id: number) {
    for (var item of Array.from(this.openedMessageThreads.values())) {
      if (item === id) {
        return true;
      }
    }
    return false;
  }

  openDialog(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: 'DELETE',
        content: 'DELETE_MESSAGE',
        button: 'DELETE',
      },
      width: '350px',
      backdropClass: 'dialog-custom-color',
      autoFocus: false
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === true && this.isIncoming) {
        this.deleteIncomingMessage(id);
      } else if (result === true && !this.isIncoming) {
        this.deleteSentMessage(id);
      }
    });
  }

  deleteIncomingMessage(id: number) {
    this.messageService.deleteIncomingMessage(id).subscribe(
      () => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'INCOMING_MESSAGE_DELETED'},
          duration: 3500,
          panelClass: ['green-snackbar'],
        });
        /*this._snackBar.open('Incoming message has been deleted.', 'OK', {
          duration: 3500,
          panelClass: ['green-snackbar']
        });*/
      },
      () => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'INCOMING_MESSAGE_DELETE_ERROR'},
          duration: 3500,
          panelClass: ['red-snackbar'],
        });
        /*this._snackBar.open('Unable to delete incoming message.', 'OK', {
          duration: 3500,
          panelClass: ['red-snackbar']
        });*/
      },
      () => {
        this.refreshMessageList();
      }
    );
  }

  deleteSentMessage(id: number) {
    this.messageService.deleteSentMessage(id).subscribe(
      () => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'SENT_MESSAGE_DELETED'},
          duration: 3500,
          panelClass: ['green-snackbar'],
        });
        /*this._snackBar.open('Sent message has been deleted.', 'OK', {
          duration: 3500,
          panelClass: ['green-snackbar']
        });*/
      },
      error => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'SENT_MESSAGE_DELETE_ERROR'},
          duration: 3500,
          panelClass: ['red-snackbar'],
        });
        /*this._snackBar.open('Unable to delete sent message.', 'OK', {
          duration: 3500,
          panelClass: ['red-snackbar']
        });*/
      },
      () => {
        this.refreshMessageList();
      }
    );
  }
}
