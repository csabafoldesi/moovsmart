import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PropertyListItemModel} from "../../models/propertyListItem.model";

@Component({
  selector: 'app-property-list-image',
  templateUrl: './property-list-image.component.html',
  styleUrls: ['./property-list-image.component.css']
})
export class PropertyListImageComponent implements OnInit {

  @Input()
  propertyList: Array<PropertyListItemModel> = [];

  @Output()
  onClick: EventEmitter<number> = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  details(id: number) {
    this.onClick.emit(id);
  }

}
