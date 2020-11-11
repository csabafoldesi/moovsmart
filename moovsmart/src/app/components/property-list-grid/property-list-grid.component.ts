import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PropertyListItemModel} from "../../models/propertyListItem.model";

@Component({
  selector: 'app-property-list-grid',
  templateUrl: './property-list-grid.component.html',
  styleUrls: ['./property-list-grid.component.css']
})
export class PropertyListGridComponent implements OnInit {

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
