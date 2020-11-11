import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from "@angular/material/dialog";
import {MapDialogComponent} from "../map-dialog/map-dialog.component";

@Component({
  selector: 'app-loading-popup',
  templateUrl: './loading-popup.component.html',
  styleUrls: ['./loading-popup.component.css']
})
export class LoadingPopupComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<MapDialogComponent>) {
  }

  ngOnInit() {
  }

}
