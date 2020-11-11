import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {environment} from "../../../environments/environment";

@Component({
  selector: 'app-map-dialog',
  templateUrl: './map-dialog.component.html',
  styleUrls: ['./map-dialog.component.css']
})
export class MapDialogComponent implements OnInit {

  mapApiKey = environment.MAP_API_KEY;

  constructor(public dialogRef: MatDialogRef<MapDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: {
                location: { lng: number, lat: number },
                zoom: number
              }) {
  }

  ngOnInit() {
  }

  onClose() {
    this.dialogRef.close();
  }

}
