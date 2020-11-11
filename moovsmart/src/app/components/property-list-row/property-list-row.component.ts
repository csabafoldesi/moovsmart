import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PropertyListItemModel} from "../../models/propertyListItem.model";

@Component({
  selector: 'app-property-list-row',
  templateUrl: './property-list-row.component.html',
  styleUrls: ['./property-list-row.component.css']
})
export class PropertyListRowComponent implements OnInit {
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
