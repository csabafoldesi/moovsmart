import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  isAccountInfoShown: boolean = true;
  isRegistrationFormEditShown: boolean = false;
  isOpenedSideNav: boolean = true;

  constructor(private router: Router) {
  }

  ngOnInit() {
    if (!localStorage.getItem('user')) {
      this.router.navigate(['/login-form']);
    }
  }

  showAccountInfo() {
    this.isAccountInfoShown = true;
    this.isRegistrationFormEditShown = false;
  }

  showRegistrationFormEdit() {
    this.isAccountInfoShown = false;
    this.isRegistrationFormEditShown = true;
  }

}
