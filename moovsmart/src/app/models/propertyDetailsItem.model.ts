import {PropertyTypeModel} from "./propertyType.model";
import {PropertyConditionModel} from "./propertyCondition.model";
import {PropertyHeatingModel} from "./propertyHeating.model";
import {PropertyParkingModel} from "./propertyParking.model";
import {ImageModel} from "./image.model";
import {AccountDetailsPropertyModel} from "./accountDetailsProperty.model";

export interface PropertyDetailsItemModel {
  id: number;
  name: string;
  numberOfRooms: number;
  price: number;
  floorArea: number;
  sqmPrice: number;
  lotSize: number;
  balconySize: number;
  yearBuilt: number;
  description: string;
  propertyType: PropertyTypeModel;
  propertyCondition: PropertyConditionModel;
  propertyHeating: PropertyHeatingModel;
  propertyParking: PropertyParkingModel;
  imageList: ImageModel[];
  accountDetailsProperty: AccountDetailsPropertyModel;
  country: string;
  zipCode: string;
  city: string;
  street: string;
  location: { lng: number, lat: number };
  visits: number;
  bookmarks: number;
}
