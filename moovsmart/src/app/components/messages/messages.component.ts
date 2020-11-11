import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MessageService} from "../../services/message.service";

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  isOpenedSideNav: boolean = true;
  isIncoming: boolean = true;

  constructor(private router: Router,
              private messageService: MessageService,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    if (!localStorage.getItem('user')) {
      this.router.navigate(['/login-form']);
    }
  }

  showIncomingMessages() {
    this.isIncoming = true;
  }

  showSentMessages() {
    this.isIncoming = false;
  }
}
