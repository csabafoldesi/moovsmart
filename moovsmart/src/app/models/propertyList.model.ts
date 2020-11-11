import {PropertyListItemModel} from "./propertyListItem.model";

export interface PropertyListModel {
  totalItems: number;
  propertyListItems: Array<PropertyListItemModel>;
}
