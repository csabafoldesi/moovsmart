import {Component, OnInit} from '@angular/core';
import {PropertyDetailsItemModel} from '../../models/propertyDetailsItem.model';
import {PropertyService} from '../../services/property.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AccountService} from "../../services/account.service";
import {LoginService} from "../../services/login.service";
import {AuthenticatedAccountModel} from "../../models/authenticatedAccount.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {MessageSendDialogComponent} from "../message-send-dialog/message-send-dialog.component";
import {environment} from "../../../environments/environment";
import {MapDialogComponent} from "../map-dialog/map-dialog.component";
import {BookmarkService} from "../../services/bookmark.service";
import {BookmarkModel} from "../../models/bookmark.model";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

@Component({
  selector: 'app-property-details',
  templateUrl: './property-details.component.html',
  styleUrls: ['./property-details.component.css']
})
export class PropertyDetailsComponent implements OnInit {

  propertyDetails: PropertyDetailsItemModel;
  accountInfo: AuthenticatedAccountModel;
  isDeleteButtonShown = false;
  isMessageSuggestionBoxShown = false;
  globalErrorMessage: string;
  mapApiKey = environment.MAP_API_KEY;
  bookmarked: boolean = false;
  bookmarkData: BookmarkModel;

  constructor(private propertyService: PropertyService,
              private accountService: AccountService,
              private loginService: LoginService,
              private router: Router,
              private route: ActivatedRoute,
              private _snackBar: MatSnackBar,
              private dialog: MatDialog,
              private bookmarkService: BookmarkService) {
  }

  ngOnInit() {
    if (!this.isLoggedIn()) {
      this.isMessageSuggestionBoxShown = true;
    }
    this.propertyService.snackbarSubject.subscribe(
      (message) => {
        if (message !== null) {
          this._snackBar.openFromComponent(SnackBarComponent, {
            data: {id: message.message},
            duration: 3500,
            panelClass: ['green-snackbar'],
          });
          /*this._snackBar.open(message.message, 'OK', {
            duration: 3500,
            panelClass: ['green-snackbar']
          });*/
          this.propertyService.snackbarSubject.next(null);
        }
      }
    );
    this.route.paramMap.subscribe(
      paramMap => {
        const postIdDetails = +paramMap.get('id');
        if (postIdDetails) {
          this.getPropertyDetails(postIdDetails);
        } else {
          const message = {
            message: 'WRONG_PROPERTY_ID', action: 'OK'
          };
          this.propertyService.snackbarSubject.next(message);
          this.goBackToPropertyList();
        }
      },
      error => this.goBackToPropertyList(),
    );
  }

  goBackToPropertyList(): void {
    this.propertyService.detailsPageVisited();
    this.router.navigate(['property-list', this.propertyService.myCurrentPage + 1]);
  }

  goBackToAdminPage() {
    this.router.navigate(['admin']);
  }

  onDelete(): void {
    const id = this.propertyDetails.id;
    if (id > 0) {
      // TODO: translate this confirm message
      if (confirm('Do you really want to delete this property?')) {
        this.propertyService.deleteProperty(id).subscribe(
          () => {
            let message;
            if (this.accountInfo.role === 'ROLE_OWNER') {
              message = {
                message: 'PROPERTY_DELETED', action: 'OK'
              }
            } else {
              message = {
                message: 'PROPERTY_DELETED_BY_ADMIN',
                action: 'OK'
              }
            }
            this.propertyService.snackbarSubject.next(message);
          },
          error => {
            this.handleError(error);
          },
          () => {
            this.goBackToPropertyList();
          }
        );
      }
    }
  }

  isAdminRole() {
    return localStorage.getItem('role') === 'ROLE_ADMIN';
  }

  isLoggedIn() {
    return localStorage.getItem('user');
  }

  isDifferentAccount() {
    const emailAddress = this.propertyDetails.accountDetailsProperty.email;
    let isDifferent: boolean = true;
    if (this.accountInfo.emailAddress === emailAddress) {
      isDifferent = false;
    }
    return isDifferent;
  }

  showForm() {
    if (this.isLoggedIn() && this.isDifferentAccount()) {
      this.dialog.open(MessageSendDialogComponent, {
        data: {
          accountDetails: this.propertyDetails.accountDetailsProperty,
          propertyId: this.propertyDetails.id,
        }
      });
    } else if (!this.isLoggedIn()) {
      this.loginService.redirectSubject.next(this.propertyDetails.id);
      this.router.navigate(['/login-form']);
    }
  }

  private handleError(error) {
    let message: string;
    if (error.status === 403) {
      message = 'ACCESS_DENIED'
    } else if (error.status === 400) {
      message = 'PROPERTY_DELETE_ERROR'
    }
    this._snackBar.openFromComponent(SnackBarComponent, {
      data: {id: message},
      duration: 3500,
      panelClass: ['red-snackbar'],
    });
    /*this._snackBar.open(message, 'OK', {
      duration: 3500,
      panelClass: ['red-snackbar']
    });*/
  }

  private getPropertyDetails(id: number): void {
    this.propertyService.fetchPropertyDetails(id).subscribe(
      (data: PropertyDetailsItemModel) => {
        this.propertyDetails = data;
        this.getAccountInfo();
      },
      () => {
        const message = {
          message: 'WRONG_PROPERTY_ID', action: 'OK'
        };
        this.propertyService.snackbarSubject.next(message);
        this.goBackToPropertyList();
      });
  }

  bookmarkClick = () => {
    if (this.bookmarked) {
      this.deleteBookmark();
    } else {
      this.saveBookmark();
    }
  }

  saveBookmark = () => {
    if (this.isLoggedIn()) {
      this.bookmarkData = this.getBookmarkData();

      this.bookmarkService.createBookmark(this.bookmarkData).subscribe(
        () => {
          this.bookmarked = true;
          this.propertyDetails.bookmarks++;
        }
      );
    }
  }

  deleteBookmark = () => {
    if (this.isLoggedIn()) {
      this.bookmarkData = this.getBookmarkData();

      this.bookmarkService.deleteBookmark(this.bookmarkData).subscribe(
        () => {
          this.bookmarked = false;
          this.propertyDetails.bookmarks--;
        }
      );
    }
  }

  private getBookmarkData() {
    return {
      accountEmail: this.accountInfo.emailAddress,
      propertyId: this.propertyDetails.id
    };
  }

  private handleLayout() {
    if (this.isLoggedIn() && this.isDifferentAccount() && !this.loginService.isRedirectBackToPropertyDetails) {
      this.isMessageSuggestionBoxShown = true;
    } else if (this.isLoggedIn() && this.isDifferentAccount() && this.loginService.isRedirectBackToPropertyDetails) {
      this.isMessageSuggestionBoxShown = true;
      this.loginService.isRedirectBackToPropertyDetails = false;
      this.showForm();
    } else if (this.isLoggedIn() && !this.isDifferentAccount()) {
      this.isDeleteButtonShown = true;
      this.isMessageSuggestionBoxShown = false;
    }
  }

  onEdit(): void {
    if (this.propertyDetails.id) {
      this.router.navigate(['/property-form/' + this.propertyDetails.id]);
    }
  }

  openMap(): void {
    this.dialog.open(MapDialogComponent, {
      data: {
        location: this.propertyDetails.location,
        zoom: 13
      }
    });

  }

  private getAccountInfo(): void {
    this.accountService.accountInfo().subscribe(
      (response) => {
        this.accountInfo = response;
        this.checkBookmarkState();
      },
      () => {
      },
      () => {
        this.handleLayout();
      }
    );
  }

  private checkBookmarkState() {
    if (this.accountInfo) {
      this.bookmarkData = this.getBookmarkData();
      this.bookmarkService.alreadySaved(this.bookmarkData).subscribe(
        saved => {
          this.bookmarked = saved;
        },
        error => console.log(error)
      );
    }
  }

}
