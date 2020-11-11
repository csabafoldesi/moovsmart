import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {LoginService} from "../../services/login.service";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {validationHandler} from "../../utils/validationHandler";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AccountService} from "../../services/account.service";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit, OnDestroy {

  loginForm: FormGroup;
  redirectBackInfo: any;
  snackBarSubscription;
  redirectSubscription;

  constructor(private loginService: LoginService,
              private accountService: AccountService,
              private http: HttpClient,
              private router: Router,
              private _snackBar: MatSnackBar) {
    this.loginForm = new FormGroup({
      'emailAddress': new FormControl(''),
      'password': new FormControl(''),
    });
  }

  ngOnInit() {
    if (localStorage.getItem('user')) {
      this.router.navigate(['/property-list']);
    }

    this.snackBarSubscription = this.accountService.snackbarSubject.subscribe(
      (response) => {
        if (response !== null) {
          this.showSnackBar(response);
          this.accountService.snackbarSubject.next(null);
        }
      }
    );

    this.redirectSubscription = this.loginService.redirectSubject.subscribe(
      (response) => {
        if (response !== null) {
          this.redirectBackInfo = response;
          const message = {
            message: 'LOGIN_TO_SEND_MESSAGE', action: 'OK'
          };
          this.showSnackBar(message);
          this.loginService.redirectSubject.next(null);
        }
      }
    );

  }

  ngOnDestroy() {
    if (this.snackBarSubscription) {
      this.snackBarSubscription.unsubscribe();
    }
    if (this.redirectSubscription) {
      this.redirectSubscription.unsubscribe();
    }
  }

  submit() {
    const data = {...this.loginForm.value};
    this.loginService.authenticate(data).subscribe(
      response => {
        localStorage.setItem('user', JSON.stringify(response));
        localStorage.setItem('role', response.role);
        this.loginService.badgeSubject.next();
      },
      error => {
        this.handleError(error);
      },
      () => {
        this.navigate();
      }
    );
  }

  private showSnackBar(response) {
    if (response.message === 'LOGIN_TO_SEND_MESSAGE') {
      this._snackBar.openFromComponent(SnackBarComponent, {
        data: {id: response.message},
        duration: 10000,
        panelClass: ['normal-snackbar'],
      });
      /*this._snackBar.open(response.message, response.action, {
        duration: 10000,
        panelClass: ['normal-snackbar']
      });*/
    } else {
      this._snackBar.openFromComponent(SnackBarComponent, {
        data: {id: response.message},
        duration: 10000,
        panelClass: ['green-snackbar'],
      });
      /*this._snackBar.open(response.message, response.action, {
        duration: 10000,
        panelClass: ['green-snackbar']
      });*/
    }
  }

  private handleError(error) {
    if (error.status === 401) {
      this.loginForm.get('emailAddress').setErrors({serverError: 'WRONG_PASSWORD'});
    } else {
      validationHandler(error, this.loginForm);
    }
  }

  private navigate() {
    if (this.redirectBackInfo === null || this.redirectBackInfo === undefined) {
      this.router.navigate(['/property-list']);
    } else {
      this.loginService.isRedirectBackToPropertyDetails = true;
      this.router.navigate(['/property-details', this.redirectBackInfo]);
    }
  }

  openPasswordChange() {
    this.accountService.isPasswordChange = true;
    this.router.navigate(['/activation']);
  }
}
