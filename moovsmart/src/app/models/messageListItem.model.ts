import {AccountDetailsMessageModel} from "./accountDetailsMessage.model";
import {PropertyDetailsMessageModel} from "./propertyDetailsMessage.model";

export interface MessageListItemModel {
  id: number,
  content: string,
  timestampCreated: Date,
  unRead?: boolean,
  fromAccountDetailsMessage?: AccountDetailsMessageModel;
  toAccountDetailsMessage?: AccountDetailsMessageModel;
  propertyDetailsMessage: PropertyDetailsMessageModel;
}
