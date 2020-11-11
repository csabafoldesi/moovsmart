import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../services/account.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {FormControl, FormGroup} from "@angular/forms";
import {validationHandler} from "../../utils/validationHandler";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit {

  isActivationKey = false;
  isSuccessful = true;
  isResendEmail = false;
  isPasswordChange = false;

  resendForm: FormGroup;
  resetForm: FormGroup;

  constructor(private accountService: AccountService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private _snackBar: MatSnackBar) {
    this.resendForm = new FormGroup({
      'emailResend': new FormControl(''),
    });
    this.resetForm = new FormGroup({
      'emailReset': new FormControl(''),
    });
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      paramMap => {
        const activationKey = paramMap.get('activationKey');
        if (activationKey) {
          this.isActivationKey = true;
          this.accountService.activateAccount(activationKey).subscribe(
            () => {
              this.showSuccessfulMessage();
            },
            () => {
              this.showUnsuccessfulMessage();
            },
            () => {
              this.navigate();
            }
          );
        }
      },
      error => console.warn(error)
    );

    this.handleLayout();
  }

  private handleLayout() {
    if (this.accountService.isResendEmail) {
      this.isResendEmail = true;
      this.accountService.isResendEmail = false;
    }

    if (this.accountService.isPasswordChange) {
      this.isPasswordChange = true;
      this.accountService.isPasswordChange = false;
    }

    if (!this.isResendEmail && !this.isPasswordChange && !this.isActivationKey) {
      this.router.navigate(['/login-form']);
    }
  }

  submitResend() {
    const data = {...this.resendForm.value};
    this.accountService.resendActivationEmail(data).subscribe(
      () => {
        const message = {message: 'EMAIL_RESENT', action: 'OK'};
        this.accountService.snackbarSubject.next(message);
      },
      error => validationHandler(error, this.resendForm),
      () => {
        this.router.navigate(['/login-form']);
      }
    );
  }

  submitReset() {
    const data = {...this.resetForm.value};
    this.accountService.sendNewPasswordEmail(data).subscribe(
      () => {
        const message = {message: 'NEW_PASSWORD_LINK_SENT', action: 'OK'};
        this.accountService.snackbarSubject.next(message);
      },
      error => validationHandler(error, this.resetForm),
      () => {
        this.router.navigate(['/login-form']);
      }
    );
  }

  private showUnsuccessfulMessage() {
    this.isSuccessful = false;
    this._snackBar.openFromComponent(SnackBarComponent, {
      data: {id: 'UNSUCCESSFUL_ACTIVATION'},
      duration: 3500,
      panelClass: ['red-snackbar'],
    });
    /*this._snackBar.open('Unsuccessful activation!', 'OK', {
      duration: 3500,
      panelClass: ['red-snackbar']
    });*/
  }

  private showSuccessfulMessage() {
    this.isSuccessful = true;
    this._snackBar.openFromComponent(SnackBarComponent, {
      data: {id: 'SUCCESSFUL_ACTIVATION'},
      duration: 2000,
      panelClass: ['green-snackbar'],
    });
    /*this._snackBar.open('Successful activation.', 'OK', {
      duration: 2000,
      panelClass: ['green-snackbar']
    });*/
  }

  private navigate() {
    setTimeout(() => {
      this.router.navigate(['/login-form']);
    }, 2000);
  }

}
