import {Injectable} from '@angular/core';
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  sessionInfo = new Subject<boolean>();

  constructor() {
  }

  sessionState(): Observable<boolean> {
    return this.sessionInfo.asObservable();
  }

  setSession(isValid: boolean) {
    this.sessionInfo.next(isValid);
  }

}
