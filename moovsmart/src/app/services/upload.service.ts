import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ImageResourceModel} from "../models/imageResource.model";
import {environment} from "../../environments/environment";
import {ImageModel} from "../models/image.model";

@Injectable({
  providedIn: 'root'
})
export class UploadService {

  BASE_URL: string = environment.BASE_URL + '/api/uploads/';

  constructor(private httpClient: HttpClient) {
  }

  uploadFile(file: File): Observable<ImageResourceModel> {
    const formData: FormData = new FormData();
    formData.append('file', file);

    return this.httpClient.post<ImageResourceModel>(this.BASE_URL, formData);
    /*return this.httpClient.post<HttpEvent<any>>(this.BASE_URL, formData, {
      reportProgress: true,
      responseType: 'json'
    });*/
  }

  deleteFile(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.BASE_URL + id);
  }

  updateFileTitle(image: ImageModel): Observable<any> {
    return this.httpClient.put<any>(this.BASE_URL + image.id, image);
  }

}
