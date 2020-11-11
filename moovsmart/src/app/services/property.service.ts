import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {PropertyFormDataModel} from '../models/propertyFormData.model';
import {PropertyDetailsItemModel} from '../models/propertyDetailsItem.model';
import {PropertyFormInitDataModel} from "../models/propertyFormInitData.model";
import {environment} from "../../environments/environment";
import {PropertyListModel} from "../models/propertyList.model";
import {isNull} from 'util';
import {PropertyListFilterModel} from "../models/propertyListFilter.model";
import {BatchLoadResultModel} from "../models/batchLoadResult.model";

@Injectable({
  providedIn: 'root'
})
export class PropertyService {

  baseUrl = environment.BASE_URL + '/api/properties/';

  snackbarSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);

  myCurrentPage: number = 0;
  myPageSize: number = 10;
  mySorting: string = 'id.desc';
  backFromDetailsPage: boolean = false;
  mySearchParameter: PropertyListFilterModel;

  backToAdminDashboard: boolean = false;
  adminCurrentPage: number = 0;
  adminPageSize: number = 10;
  adminSorting: string = 'id.desc';
  adminSelectedTabIndex: number = 0;

  constructor(private httpClient: HttpClient) {
  }

  getActiveProperties(page: number, pageSize: number, sortBy: String): Observable<PropertyListModel> {
    return this.httpClient.get<PropertyListModel>(this.baseUrl +
      'filtered?page=' + page +
      '&pageSize=' + pageSize +
      '&sortBy=' + sortBy);
  }

  getActivePropertiesWithFilter(page: number, pageSize: number, sortBy: String, search: PropertyListFilterModel): Observable<PropertyListModel> {
    let params = new HttpParams();
    for (const s in search) {
      if (!isNull(search[s]) && search[s] !== '') {
        params = params.set(s, search[s]);
      }
    }

    return this.httpClient.get<PropertyListModel>(this.baseUrl +
      'filtered?page=' + page +
      '&pageSize=' + pageSize +
      '&sortBy=' + sortBy +
      '&' + params.toString()
    );
  }

  getPendingProperties(page: number, pageSize: number, sortBy: string): Observable<PropertyListModel> {
    return this.httpClient.get<PropertyListModel>(this.baseUrl +
      'admin?page=' + page +
      '&pageSize=' + pageSize +
      '&sortBy=' + sortBy +
      '&status=PENDING');
  }

  getInactiveProperties(page: number, pageSize: number, sortBy: string): Observable<PropertyListModel> {
    return this.httpClient.get<PropertyListModel>(this.baseUrl +
      'admin?page=' + page +
      '&pageSize=' + pageSize +
      '&sortBy=' + sortBy +
      '&status=INACTIVE');
  }

  fetchPropertyDetails(id: number): Observable<PropertyDetailsItemModel> {
    return this.httpClient.get<PropertyDetailsItemModel>(this.baseUrl + 'filtered/' + id);
  }

  fetchPropertyDetailsForUpdate(id: number): Observable<PropertyDetailsItemModel> {
    return this.httpClient.get<PropertyDetailsItemModel>(this.baseUrl + 'for-update/' + id);
  }

  getPropertyFormInitData(): Observable<PropertyFormInitDataModel> {
    return this.httpClient.get<PropertyFormInitDataModel>(this.baseUrl + 'property-form-init-data')
  }

  createProperty(propertyFormData: PropertyFormDataModel): Observable<any> {
    return this.httpClient.post(this.baseUrl, propertyFormData);
  }

  activateProperty(propertyId: any) {
    return this.httpClient.put(this.baseUrl + 'admin/property-activation/' + propertyId, null);
  }

  inactivateProperty(propertyId: any) {
    return this.httpClient.put(this.baseUrl + 'admin/property-inactivation/' + propertyId, null);
  }

  updateProperty(id: number, data: PropertyFormDataModel): Observable<any> {
    return this.httpClient.put(this.baseUrl + id, data);
  }

  deleteProperty(id: number): Observable<any> {
    return this.httpClient.delete(this.baseUrl + id);
  }

  addPageDetails(page: number, pageSize: number, sortBy: string, search: PropertyListFilterModel) {
    this.myCurrentPage = page;
    this.myPageSize = pageSize;
    this.mySorting = sortBy;
    this.mySearchParameter = search;
  }

  addAdminPageDetails(page: number, pageSize: number, sortBy: string, selectedTabIndex: number) {
    this.adminCurrentPage = page;
    this.adminPageSize = pageSize;
    this.adminSorting = sortBy;
    this.adminSelectedTabIndex = selectedTabIndex;
  }

  detailsPageVisited() {
    this.backFromDetailsPage = true;
  }

  detailsPageVisitedFromAdminDashboard() {
    this.backToAdminDashboard = true;
  }

  redirectedBackToAdminDashboard() {
    this.backToAdminDashboard = false;
  }

  getCities(keyword: string) {
    return this.httpClient.get<Array<string>>(this.baseUrl + 'city?keyword=' + keyword);
  }

  uploadBatchUploadFile(file: File): Observable<BatchLoadResultModel> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.httpClient.post<BatchLoadResultModel>(this.baseUrl + 'batch', formData);
  }

}
