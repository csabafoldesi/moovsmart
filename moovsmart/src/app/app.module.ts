import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PropertyFormComponent} from './components/property-form/property-form.component';
import {PropertyDetailsComponent} from './components/property-details/property-details.component';
import {PropertyListComponent} from './components/property-list/property-list.component';
import {RegistrationFormComponent} from './components/registration-form/registration-form.component';
import {ImageSelectorComponent} from './components/image-selector/image-selector.component';
import {LoginFormComponent} from "./components/login-form/login-form.component";
import {HttpRequestInterceptor} from "./utils/httpRequestInterceptor";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NavigationBarComponent} from './components/navigation-bar/navigation-bar.component';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MatCardModule} from "@angular/material/card";
import {ActivationComponent} from "./components/activation/activation.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MessagesComponent} from './components/messages/messages.component';
import {MatSelectModule} from "@angular/material/select";
import {MatTabsModule} from "@angular/material/tabs";
import {MessageFormComponent} from './components/message-form/message-form.component';
import {MaterialFileInputModule} from "ngx-material-file-input";
import {MatBadgeModule} from "@angular/material/badge";
import {MatExpansionModule} from "@angular/material/expansion";
import {MessageListComponent} from './components/message-list/message-list.component';
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {ConfirmDialogComponent} from './components/confirm-dialog/confirm-dialog.component';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from "@angular/material/dialog";
import {MessageThreadListComponent} from './components/message-thread-list/message-thread-list.component';
import {ImageViewerComponent} from './components/image-viewer/image-viewer.component';
import {ProfileComponent} from './components/profile/profile.component';
import {AccountInfoComponent} from './components/account-info/account-info.component';
import {MatTooltipModule} from "@angular/material/tooltip";
import {AvatarPhotoComponent} from './components/avatar-photo/avatar-photo.component';
import {PropertyListFilterComponent} from './components/property-list-filter/property-list-filter.component';
import {MatMenuModule} from "@angular/material/menu";
import {AccountPasswordComponent} from './components/account-password/account-password.component';
import {MessageSendDialogComponent} from './components/message-send-dialog/message-send-dialog.component';
import {MapDialogComponent} from './components/map-dialog/map-dialog.component';
import {AgmCoreModule} from "@agm/core";
import {environment} from "../environments/environment";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {BatchUploadComponent} from './components/batch-upload/batch-upload.component';
import {BatchUploadResultComponent} from './components/batch-upload-result/batch-upload-result.component';
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {PropertyListImageComponent} from './components/property-list-image/property-list-image.component';
import {PropertyListGridComponent} from './components/property-list-grid/property-list-grid.component';
import {AdminDashboardComponent} from './components/admin-dashboard/admin-dashboard.component';
import {MatTableModule} from "@angular/material/table";
import {PropertyListRowComponent} from './components/property-list-row/property-list-row.component';
import {LoadingPopupComponent} from './components/loading-popup/loading-popup.component';
import {BookmarkListComponent} from './components/bookmark-list/bookmark-list.component';
import {SnackBarComponent} from './components/snack-bar/snack-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    PropertyFormComponent,
    PropertyDetailsComponent,
    PropertyListComponent,
    RegistrationFormComponent,
    ImageSelectorComponent,
    LoginFormComponent,
    NavigationBarComponent,
    ActivationComponent,
    MessagesComponent,
    MessageFormComponent,
    MessageListComponent,
    ConfirmDialogComponent,
    MessageThreadListComponent,
    ImageViewerComponent,
    ProfileComponent,
    AccountInfoComponent,
    AvatarPhotoComponent,
    PropertyListFilterComponent,
    AccountPasswordComponent,
    MessageSendDialogComponent,
    MapDialogComponent,
    BatchUploadComponent,
    BatchUploadResultComponent,
    PropertyListImageComponent,
    PropertyListGridComponent,
    PropertyListImageComponent,
    BatchUploadResultComponent,
    AdminDashboardComponent,
    PropertyListRowComponent,
    LoadingPopupComponent,
    BookmarkListComponent,
    SnackBarComponent,
  ],
  entryComponents: [
    ConfirmDialogComponent,
    MessageSendDialogComponent,
    MapDialogComponent,
    LoadingPopupComponent,
    SnackBarComponent],
  imports: [
    HttpClientModule,
    ReactiveFormsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatTabsModule,
    MatProgressSpinnerModule,
    MaterialFileInputModule,
    MatBadgeModule,
    MatExpansionModule,
    MatPaginatorModule,
    MatSortModule,
    MatDialogModule,
    MatTooltipModule,
    MatMenuModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: environment.MAP_API_KEY
    }),
    MatCheckboxModule,
    MatAutocompleteModule,
    MatGridListModule,
    MatButtonToggleModule,
    MatTableModule,
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: HttpRequestInterceptor, multi: true},
    {provide: MatDialogRef, useValue: {}},
    {provide: MAT_DIALOG_DATA, useValue: {}}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
