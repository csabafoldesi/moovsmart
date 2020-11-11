import {Component, Directive, EventEmitter, OnInit, Output} from '@angular/core';
import {AccountProfileDetailsModel} from "../../models/accountProfileDetails.model";
import {AccountService} from "../../services/account.service";
import {LogoutService} from "../../services/logout.service";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDialogComponent} from "../confirm-dialog/confirm-dialog.component";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css']
})

export class AccountInfoComponent implements OnInit {

  accountProfileDetails: AccountProfileDetailsModel;
  @Output() openAccountEditEmitter: EventEmitter<any> = new EventEmitter();

  constructor(private accountService: AccountService,
              private logoutService: LogoutService,
              private router: Router,
              private _snackBar: MatSnackBar,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    if (!localStorage.getItem('user')) {
      this.router.navigate(['/login-form']);
    }

    this.accountService.getAccountProfileDetails().subscribe(
      (response) => {
        this.accountProfileDetails = response;
      }
    );
  }

  openAccountEdit() {
    this.accountService.isEditionSubject.next(true);
    this.openAccountEditEmitter.emit();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: 'DELETE',
        content: 'DELETE_ACCOUNT',
        button: 'DELETE',
      },
      width: '350px',
      backdropClass: 'dialog-custom-color',
      autoFocus: false
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.deleteAccount();
      }
    });
  }

  deleteAccount() {
    this.accountService.deleteAccount().subscribe(
      () => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'ACCOUNT_DELETED'},
          duration: 10000,
          panelClass: ['green-snackbar'],
        });
      }, () => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'ACCOUNT_UNABLE_DELETE'},
          duration: 3500,
          panelClass: ['red-snackbar'],
        });
      },
      () => {
        this.handleLogout();
      }
    );
  }

  private handleLogout() {
    this.logoutService.logout().subscribe(
      () => {
        localStorage.removeItem('user');
        localStorage.removeItem('role');
      }
    );
    setTimeout(() => {
      this.router.navigate(['/property-list']);
    }, 3500);
  }
}
