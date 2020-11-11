import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountService} from "../../services/account.service";
import {validationHandler} from "../../utils/validationHandler";
import {AccountDetailsNewPasswordFormDataModel} from "../../models/accountDetailsNewPasswordFormData.model";

@Component({
  selector: 'app-account-password',
  templateUrl: './account-password.component.html',
  styleUrls: ['./account-password.component.css']
})
export class AccountPasswordComponent implements OnInit {

  newPasswordKey: string;
  accountDetails: AccountDetailsNewPasswordFormDataModel;

  newPasswordForm = this.formBuilder.group({
    "password": [''],
  });

  constructor(private formBuilder: FormBuilder,
              private accountService: AccountService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      paramMap => {
        const newPasswordKey = paramMap.get('newPasswordKey');
        if (newPasswordKey) {
          this.newPasswordKey = newPasswordKey;
          this.accountService.getAccountByNewPasswordKey(newPasswordKey).subscribe(
            (response) => {
              this.accountDetails = response;
            },
            () => {
              this.router.navigate(['/login-form']);
            },
            () => {
              if (this.newPasswordKey === null) {
                this.router.navigate(['/login-form']);
              }
            }
          );
        }
      },
      error => console.warn(error)
    );

  }

  submit() {
    const data = {...this.newPasswordForm.value};
    this.accountService.saveNewPassword(this.newPasswordKey, data).subscribe(
      () => {
        const message = {message: 'NEW_PASSWORD_SET', action: 'OK'};
        this.accountService.snackbarSubject.next(message);
      },
      error => validationHandler(error, this.newPasswordForm),
      () => {
        this.router.navigate(['/login-form']);
      }
    );
  }
}
