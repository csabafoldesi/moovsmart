import {RoleModel} from "./role.model";

export interface AccountProfileDetailsModel {
  fullName: string;
  email: string;
  phoneNumber: string;
  role: RoleModel;
}
