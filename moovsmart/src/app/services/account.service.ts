import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {AccountFormModel} from "../models/accountForm.model";
import {AuthenticatedAccountModel} from "../models/authenticatedAccount.model";
import {ResendEmailModel} from "../models/resendEmail.model";
import {AccountFormInitDataModel} from "../models/accountFormInitDataModel";
import {AccountProfileDetailsModel} from "../models/accountProfileDetails.model";
import {environment} from "../../environments/environment";
import {SendNewPasswordEmailModel} from "../models/sendNewPasswordEmail.model";
import {AccountNewPasswordModel} from "../models/accountNewPassword.model";
import {AccountDetailsNewPasswordFormDataModel} from "../models/accountDetailsNewPasswordFormData.model";
import {AccountStatisticsModel} from "../models/accountStatistics.model";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  baseUrl = environment.BASE_URL + '/api/accounts/';

  snackbarSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  isEditionSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  isResendEmail = false;
  isPasswordChange = false;

  constructor(private httpClient: HttpClient) {
  }

  accountInfo(): Observable<AuthenticatedAccountModel> {
    return this.httpClient.get<AuthenticatedAccountModel>(this.baseUrl + 'me');
  }

  getAccountProfileDetails(): Observable<AccountProfileDetailsModel> {
    return this.httpClient.get<AccountProfileDetailsModel>(this.baseUrl + 'profile');
  }

  getRegistrationFormInitData(): Observable<AccountFormInitDataModel> {
    return this.httpClient.get<AccountFormInitDataModel>(this.baseUrl + 'registration-form-init-data');
  }

  getAccountByNewPasswordKey(newPasswordKey: string): Observable<AccountDetailsNewPasswordFormDataModel> {
    return this.httpClient.get<AccountDetailsNewPasswordFormDataModel>(this.baseUrl + 'new-password-form-data/' + newPasswordKey);
  }

  getAccountStatistics(): Observable<AccountStatisticsModel> {
    return this.httpClient.get<AccountStatisticsModel>(this.baseUrl + 'admin/statistics');
  }

  createAccount(accountFormModel: AccountFormModel): Observable<any> {
    return this.httpClient.post(this.baseUrl, accountFormModel);
  }

  resendActivationEmail(resendEmailModel: ResendEmailModel): Observable<any> {
    return this.httpClient.post(this.baseUrl + 'send', resendEmailModel);
  }

  sendNewPasswordEmail(sendNewPasswordEmailModel: SendNewPasswordEmailModel): Observable<any> {
    return this.httpClient.post(this.baseUrl + 'new-password', sendNewPasswordEmailModel);
  }

  activateAccount(activationKey: string): Observable<any> {
    return this.httpClient.put(this.baseUrl + 'activation/' + activationKey, null);
  }

  saveNewPassword(newPasswordKey: string, accountNewPasswordModel: AccountNewPasswordModel): Observable<any> {
    return this.httpClient.put(this.baseUrl + 'new-password/' + newPasswordKey, accountNewPasswordModel);
  }

  updateAccount(accountFormModel: AccountFormModel): Observable<any> {
    return this.httpClient.put(this.baseUrl + 'update', accountFormModel);
  }

  deleteAccount() {
    return this.httpClient.delete(this.baseUrl + 'own');
  }

}
