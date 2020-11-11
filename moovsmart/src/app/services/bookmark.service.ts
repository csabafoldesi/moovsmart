import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {BookmarkModel} from "../models/bookmark.model";
import {PropertyListItemModel} from "../models/propertyListItem.model";

@Injectable({
  providedIn: 'root'
})
export class BookmarkService {

  baseUrl = environment.BASE_URL + '/api/bookmarks';

  constructor(private httpClient: HttpClient) {
  }

  createBookmark(bookmarkModel: BookmarkModel): Observable<any> {
    return this.httpClient.post(this.baseUrl, bookmarkModel);
  }

  alreadySaved(bookmarkModel: BookmarkModel): Observable<any> {
    return this.httpClient.get<boolean>(this.baseUrl +
      '/?=accountEmail=' + bookmarkModel.accountEmail +
      '&propertyId=' + bookmarkModel.propertyId
    );
  }

  deleteBookmark(bookmarkModel: BookmarkModel): Observable<any> {
    return this.httpClient.delete(this.baseUrl +
      '/?=accountEmail=' + bookmarkModel.accountEmail +
      '&propertyId=' + bookmarkModel.propertyId
    );
  }

  getMyBookmarks(): Observable<any> {
    return this.httpClient.get<PropertyListItemModel>(this.baseUrl + '/myBookmarks');
  }
}
