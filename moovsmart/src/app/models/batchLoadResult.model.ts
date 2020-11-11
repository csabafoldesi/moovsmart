import {PropertyFormDataModel} from "./propertyFormData.model";

export interface BatchLoadResultModel {
  generalError: string;
  resultItems: [
    {
      propertyForm: PropertyFormDataModel;
      errorList: {};
    }
  ];
}
