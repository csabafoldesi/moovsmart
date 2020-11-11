import {Component, OnInit} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {MessageService} from "../../services/message.service";
import {AuthenticatedAccountModel} from "../../models/authenticatedAccount.model";
import {SessionService} from "../../services/session.service";
import {Location, LocationStrategy, PathLocationStrategy} from "@angular/common";

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css'],
  providers: [Location, {provide: LocationStrategy, useClass: PathLocationStrategy}]
})
export class NavigationBarComponent implements OnInit {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  intervalId: number;
  sessionCheckTimer: number;
  badgeCounter: string = '0';
  userInfo: AuthenticatedAccountModel;
  languageCode = "en";

  constructor(private breakpointObserver: BreakpointObserver,
              private httpClient: HttpClient,
              private router: Router,
              private loginService: LoginService,
              private messageService: MessageService,
              private sessionService: SessionService,
              private location: Location) {
  }

  ngOnInit(): void {
    const languageCode = localStorage.getItem('language');
    if (languageCode) {
      this.languageCode = languageCode;
    }
    this.getUserInfoFromLocalStorage();
    if (localStorage.getItem('user')) {
      this.loadBadgeCounter();
      this.intervalId = setInterval(this.loadBadgeCounter, 3_000);
    }
    this.loginService.badgeSubject.subscribe(
      () => {
        if (localStorage.getItem('user')) {
          this.loadBadgeCounter();
          this.intervalId = setInterval(this.loadBadgeCounter, 3_000);
        }
      }
    );
    this.loginService.getUserInfo().subscribe(
      userInfo => {
        this.userInfo = userInfo;
      }
    );
    this.sessionService.sessionState().subscribe(
      sessionState => {
        if (!sessionState) {
          this.userInfo = null;
          localStorage.removeItem('user');
          localStorage.removeItem('role');
          if (this.intervalId) {
            clearInterval(this.intervalId);
          }
        }
      }
    );
    this.sessionCheckTimer = setInterval(this.getUserInfoFromLocalStorage, 1000);
  }

  private getUserInfoFromLocalStorage = () => {
    const userInfo = JSON.parse(localStorage.getItem('user'));
    if (userInfo) {
      this.userInfo = userInfo;
    } else {
      this.userInfo = null;
    }
  }

  private loadBadgeCounter = () => {
    this.messageService.getBadgeCounter().subscribe(
      response => {
        if (response.badgeCounter > +this.badgeCounter) {
          this.messageService.refreshListSubject.next();
        }
        this.badgeCounter = '' + response.badgeCounter;
      },
    );
  };

  isLoggedIn() {
    return localStorage.getItem('user');
  }

  isRoleSufficient() {
    return (localStorage.getItem('role') === 'ROLE_OWNER'
      || localStorage.getItem('role') === 'ROLE_ADMIN');
  }

  isAdminRole() {
    return localStorage.getItem('role') === 'ROLE_ADMIN';
  }

  logout() {
    this.loginService.logout().subscribe(() => {
        localStorage.removeItem('user');
        localStorage.removeItem('role');
        this.loginService.redirectSubject.next(null);
        clearInterval(this.intervalId);
      },
      () => {
      },
      () => {
        this.router.navigateByUrl('/login-form');
      });
  }

  selectLanguage(languageCode: string): void {
    if (languageCode !== this.languageCode) {
      this.languageCode = languageCode;
      localStorage.setItem('language', languageCode);
      const origin = document.location.origin;
      const pathItems = document.location.pathname.split('/');
      if (pathItems.length > 1 && (pathItems[1] === 'hu' || pathItems[1] === 'en')) {
        pathItems[1] = languageCode;
        const newUrl = origin + pathItems.join('/');
        document.location.replace(newUrl);
      }
    }

  }

}
