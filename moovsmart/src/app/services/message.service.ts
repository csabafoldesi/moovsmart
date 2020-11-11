import {Injectable} from '@angular/core';
import {MessageFormModel} from "../models/messageForm.model";
import {Observable, Subject} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {MessageListItemModel} from "../models/messageListItem.model";
import {BadgeCounterModel} from "../models/badgeCounter.model";
import {environment} from "../../environments/environment";
import {MessageListFilterModel} from "../models/messageListFilter.model";
import {MessageListModel} from "../models/messageList.model";
import {isNull} from "util";
import {PropertyDetailsMessageInitDataModel} from "../models/propertyDetailsMessageInitData.model";

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  baseUrl = environment.BASE_URL + '/api/messages/';

  snackbarSubject: Subject<any> = new Subject<any>();
  refreshListSubject: Subject<any> = new Subject<any>();

  constructor(private httpClient: HttpClient) {
  }

  getMessages(page: number, pageSize: number, sortBy: string, searchParameters: MessageListFilterModel):
    Observable<MessageListModel> {
    let params = new HttpParams();
    for (const s in searchParameters) {
      if (!isNull(searchParameters[s]) && searchParameters[s] !== '') {
        params = params.set(s, searchParameters[s]);
      }
    }

    return this.httpClient.get<MessageListModel>(this.baseUrl +
      '?page=' + page +
      '&pageSize=' + pageSize +
      '&sortBy=' + sortBy +
      '&' + params.toString()
    );
  }

  getPropertiesForMessages(isIncoming: boolean): Observable<PropertyDetailsMessageInitDataModel[]> {
    return this.httpClient.get <PropertyDetailsMessageInitDataModel[]>(
      this.baseUrl + 'properties?incoming=' + isIncoming);
  }

  getCurrentDate(): Observable<Date> {
    return this.httpClient.get <Date>(this.baseUrl + 'current-date');
  }

  getBadgeCounter(): Observable<BadgeCounterModel> {
    return this.httpClient.get <BadgeCounterModel>(this.baseUrl + 'badgeCounter');
  }

  collectThreadMessages(email: string, propertyId: number): Observable<MessageListItemModel[]> {
    return this.httpClient.get<MessageListItemModel[]>(
      this.baseUrl + 'thread/email/' + email + '/property/' + propertyId);
  }

  sendMessage(messageFormModel: MessageFormModel): Observable<any> {
    return this.httpClient.post(this.baseUrl, messageFormModel);
  }

  readMessage(messageId: number) {
    return this.httpClient.put(this.baseUrl + 'read' + '/' + messageId, null);
  }

  deleteIncomingMessage(messageId: number) {
    return this.httpClient.delete(this.baseUrl + 'incoming/' + messageId);
  }

  deleteSentMessage(messageId: number) {
    return this.httpClient.delete(this.baseUrl + 'sent/' + messageId);
  }
}
