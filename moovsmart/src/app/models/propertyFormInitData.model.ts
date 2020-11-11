import {PropertyTypeModel} from "./propertyType.model";
import {PropertyConditionModel} from "./propertyCondition.model";
import {PropertyHeatingModel} from "./propertyHeating.model";
import {PropertyParkingModel} from "./propertyParking.model";

export interface PropertyFormInitDataModel {
  propertyTypes: Array<PropertyTypeModel>;
  propertyConditions: Array<PropertyConditionModel>;
  propertyHeatings: Array<PropertyHeatingModel>;
  propertyParkings: Array<PropertyParkingModel>;
}
