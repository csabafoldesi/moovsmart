import {PropertyTypeModel} from "./propertyType.model";
import {PropertyConditionModel} from "./propertyCondition.model";
import {PropertyHeatingModel} from "./propertyHeating.model";
import {PropertyParkingModel} from "./propertyParking.model";

export interface PropertyFormDataModel {
  name: string;
  numberOfRooms: number;
  price: number;
  floorArea: number;
  lotSize: number;
  balconySize: number;
  yearBuilt: number;
  description: string;
  propertyType: PropertyTypeModel;
  propertyCondition: PropertyConditionModel;
  propertyHeating: PropertyHeatingModel;
  propertyParking: PropertyParkingModel;
  imageList: number[];
  country: string;
  zipCode: string;
  city: string;
  street: string;
}
