import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

const BASE_URL: string = environment.BASE_URL;

@Injectable({
  providedIn: 'root'
})
export class LogoutService {

  constructor(private httpClient: HttpClient) {
  }

  logout(): Observable<any> {
    return this.httpClient.get(BASE_URL + '/api/logout');
  }
}
