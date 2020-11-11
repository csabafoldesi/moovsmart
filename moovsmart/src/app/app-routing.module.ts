import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PropertyListComponent} from "./components/property-list/property-list.component";
import {PropertyFormComponent} from "./components/property-form/property-form.component";
import {PropertyDetailsComponent} from "./components/property-details/property-details.component";
import {RegistrationFormComponent} from "./components/registration-form/registration-form.component";
import {LoginFormComponent} from "./components/login-form/login-form.component";
import {ActivationComponent} from "./components/activation/activation.component";
import {MessagesComponent} from "./components/messages/messages.component";
import {ProfileComponent} from "./components/profile/profile.component";
import {AccountPasswordComponent} from "./components/account-password/account-password.component";
import {BatchUploadComponent} from "./components/batch-upload/batch-upload.component";
import {AdminDashboardComponent} from "./components/admin-dashboard/admin-dashboard.component";
import {BookmarkListComponent} from "./components/bookmark-list/bookmark-list.component";

const routes: Routes = [
  {path: "", component: PropertyListComponent},
  {path: "property-list", component: PropertyListComponent},
  {path: "property-list/:page", component: PropertyListComponent},
  {path: "property-form", component: PropertyFormComponent},
  {path: "property-form/:id", component: PropertyFormComponent},
  {path: "property-details/:id", component: PropertyDetailsComponent},
  {path: "registration-form", component: RegistrationFormComponent},
  {path: "login-form", component: LoginFormComponent},
  {path: "profile", component: ProfileComponent},
  {path: "admin", component: AdminDashboardComponent},
  {path: "activation", component: ActivationComponent},
  {path: "activation/:activationKey", component: ActivationComponent},
  {path: "new-password/:newPasswordKey", component: AccountPasswordComponent},
  {path: "messages", component: MessagesComponent},
  {path: "messages/:page", component: MessagesComponent},
  {path: "batch-upload", component: BatchUploadComponent},
  {path: "bookmark", component: BookmarkListComponent},
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
