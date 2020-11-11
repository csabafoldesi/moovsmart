import {Injectable} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest,} from '@angular/common/http';

import {Observable} from 'rxjs';
import {SessionService} from "../services/session.service";

/** Inject With Credentials into the request */
@Injectable()
export class HttpRequestInterceptor implements HttpInterceptor {

  constructor(private sessionService: SessionService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    const localeId = localStorage.getItem('language') ? localStorage.getItem('language') : 'en';
    //const acceptLanguage = `${localeId}-${localeId.toUpperCase()}`;
    //console.log("interceptor: " + req.url);
    const requestWithHeader = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest').set('Accept-Language', localeId),
      withCredentials: true
    });

    return new Observable<HttpEvent<any>>((subscriber => {
      next.handle(requestWithHeader).subscribe(
        response => {
          subscriber.next(response);
        }, error => {
          if (error instanceof HttpErrorResponse && error.status === 401) {
            this.sessionService.sessionInfo.next(false);
          }
          subscriber.error(error);
        }, () => {
          subscriber.complete();
        });
    }));

    //return next.handle(requestWithHeader);
  }
}
