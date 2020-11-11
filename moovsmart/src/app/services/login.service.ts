import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable, Subject} from "rxjs";
import {AuthenticatedAccountModel} from "../models/authenticatedAccount.model";
import {environment} from "../../environments/environment";

const BASE_URL: string = environment.BASE_URL + '/api/accounts';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  redirectSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  badgeSubject: Subject<any> = new Subject<any>();
  userInfo = new Subject<AuthenticatedAccountModel>();

  isRedirectBackToPropertyDetails: boolean = false;

  constructor(private httpClient: HttpClient) {
  }

  getUserInfo(): Observable<AuthenticatedAccountModel> {
    return this.userInfo.asObservable();
  }

  authenticate(credentials): Observable<AuthenticatedAccountModel> {

    const headers = new HttpHeaders(credentials ? {
      authorization: 'Basic ' + btoa(credentials.emailAddress + ':' + credentials.password),
    } : {});

    return new Observable<any>((subscriber => {
      this.httpClient.get<AuthenticatedAccountModel>(BASE_URL + '/me', {headers: headers}).subscribe(
        response => {
          this.userInfo.next(response);
          subscriber.next(response);
        }, error => {
          subscriber.error(error);
        }, () => {
          subscriber.complete();
        });
    }));
    //return this.httpClient.get<AuthenticatedAccountModel>(BASE_URL + '/me', {headers: headers});
  }

  logout(): Observable<any> {
    return new Observable<any>((subscriber => {
      this.httpClient.get(environment.BASE_URL + '/api/logout').subscribe(
        response => {
          this.userInfo.next(null);
          subscriber.next(response);
        }, error => {
          subscriber.error(error);
        }, () => {
          subscriber.complete();
        });
    }));
  }


  /*checkSession(): Observable<AuthenticatedAccountModel> {
    return this.httpClient.get<AuthenticatedAccountModel>
    (BASE_URL + '/me');
  }*/
}
