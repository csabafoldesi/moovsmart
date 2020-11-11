import {Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {AccountService} from "../../services/account.service";
import {validationHandler} from "../../utils/validationHandler";
import {Router} from "@angular/router";
import {AccountFormInitDataModel} from "../../models/accountFormInitDataModel";
import {AccountProfileDetailsModel} from "../../models/accountProfileDetails.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit, OnDestroy {

  accountProfileDetails: AccountProfileDetailsModel;
  isEdited: boolean = false;
  @Output() openAccountDetailsEmitter: EventEmitter<any> = new EventEmitter();

  registrationForm = this.formBuilder.group({
    "fullName": [''],
    "email": [''],
    "passwordOriginal": [''],
    "password": [''],
    "passwordConfirm": [''],
    "role": [''],
    "phoneNumber": ['']
  });

  registrationFormInitData: AccountFormInitDataModel;
  isEditedSubscription;

  constructor(private formBuilder: FormBuilder,
              private accountService: AccountService,
              private router: Router,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.accountService.getRegistrationFormInitData().subscribe(
      (response) => {
        this.registrationFormInitData = response;
      }
    );
    this.isEditedSubscription = this.accountService.isEditionSubject.subscribe(
      (response) => {
        if (response !== null) {
          this.isEdited = true;
          this.accountService.getAccountProfileDetails().subscribe(
            (formData) => {
              this.accountProfileDetails = formData;
            },
            () => {
            },
            () => {
              this.patchForm();
              this.accountService.isEditionSubject.next(null);
            }
          );
        }
      }
    );
  }

  private patchForm() {
    this.registrationForm.patchValue({
      fullName: this.accountProfileDetails.fullName,
      email: this.accountProfileDetails.email,
      role: this.accountProfileDetails.role.role,
      phoneNumber: this.accountProfileDetails.phoneNumber,
    });
  }

  ngOnDestroy() {
    if (this.isEditedSubscription) {
      this.isEditedSubscription.unsubscribe();
    }
  }

  isAdminRole() {
    return localStorage.getItem('role') === 'ROLE_ADMIN';
  }

  submit() {
    if (!this.isEdited) {
      this.registerAccount();
    } else {
      this.editAccount();
    }
  }

  private registerAccount() {
    const data = {...this.registrationForm.value};

    this.accountService.createAccount(data).subscribe(
      () => {
        const message = {
          message: 'SUCCESSFUL_REGISTRATION', action: 'OK'
        };
        this.accountService.snackbarSubject.next(message);
      },
      error => validationHandler(error, this.registrationForm),
      () => {
        this.registrationForm.reset();
        this.router.navigate(['/login-form']);
      }
    );
  }

  private editAccount() {
    const data = {...this.registrationForm.value};

    this.accountService.updateAccount(data).subscribe(
      () => {
        localStorage.setItem('user', JSON.stringify({
          fullName: data.fullName,
          emailAddress: data.email,
          role: data.role
        }));
        localStorage.setItem('role', data.role);
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'ACCOUNT_UPDATED'},
          duration: 3500,
          panelClass: ['green-snackbar'],
        });
        /*this._snackBar.open('Account has been updated.', 'OK', {
          duration: 3500,
          panelClass: ['green-snackbar']
        });*/
        this.openAccountDetailsEmitter.emit();
      },
      error => validationHandler(error, this.registrationForm),
      () => {
      }
    );
  }

  openResendEmail() {
    this.accountService.isResendEmail = true;
    this.router.navigate(['/activation']);
  }
}
