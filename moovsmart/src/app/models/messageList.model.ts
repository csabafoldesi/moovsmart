import {MessageListItemModel} from "./messageListItem.model";

export interface MessageListModel {
  totalItems: number;
  messageListItems: Array<MessageListItemModel>;
}
