import {Component, OnInit} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {PropertyService} from "../../services/property.service";
import {ActivatedRoute, Router} from "@angular/router";
import {validationHandler} from "../../utils/validationHandler";
import {PropertyFormInitDataModel} from "../../models/propertyFormInitData.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {AuthenticatedAccountModel} from "../../models/authenticatedAccount.model";
import {AccountService} from "../../services/account.service";
import {PropertyFormDataModel} from "../../models/propertyFormData.model";
import {ImageModel} from "../../models/image.model";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

@Component({
  selector: 'app-property-form',
  templateUrl: './property-form.component.html',
  styleUrls: ['./property-form.component.css']
})
export class PropertyFormComponent implements OnInit {

  propertyForm = this.formBuilder.group({
    name: [''],
    numberOfRooms: [''],
    price: [''],
    floorArea: [''],
    lotSize: [''],
    balconySize: [''],
    yearBuilt: [''],
    description: [''],
    propertyType: [''],
    propertyCondition: [''],
    propertyHeating: [''],
    propertyParking: [''],
    country: [''],
    zipCode: [''],
    city: [''],
    street: [''],
  });

  imageList: ImageModel[] = [];
  propertyFormInitData: PropertyFormInitDataModel;
  accountInfo: AuthenticatedAccountModel;
  propertyId = 0;

  constructor(private formBuilder: FormBuilder,
              private propertyService: PropertyService,
              private accountService: AccountService,
              private router: Router,
              private route: ActivatedRoute,
              private _snackBar: MatSnackBar) {
  }

  ngOnInit() {
    if (!localStorage.getItem('user')) {
      this.router.navigate(['/login-form']);
    }
    this.accountService.accountInfo().subscribe(
      (response) => {
        this.accountInfo = response;
      }, () => {
      },
      () => {
        if (this.accountInfo.role !== 'ROLE_OWNER'
          && this.accountInfo.role !== 'ROLE_ADMIN') {
          this.router.navigate(['/property-list']);
        }
      }
    );
    this.propertyService.getPropertyFormInitData().subscribe(
      (response) => {
        this.propertyFormInitData = response;
      }
    );
    this.route.paramMap.subscribe(
      paramMap => {
        const propertyId = +paramMap.get('id');
        if (propertyId) {
          this.loadFormData(propertyId);
        }
      });
  }

  loadFormData(propertyId: number): void {
    this.propertyService.fetchPropertyDetailsForUpdate(propertyId).subscribe(
      response => {
        this.propertyId = propertyId;
        this.propertyForm.patchValue({
          name: response.name,
          numberOfRooms: response.numberOfRooms,
          price: response.price,
          floorArea: response.floorArea,
          lotSize: response.lotSize,
          balconySize: response.balconySize,
          yearBuilt: response.yearBuilt,
          description: response.description,
          propertyType: response.propertyType.propertyType,
          propertyCondition: response.propertyCondition.propertyCondition,
          propertyHeating: response.propertyHeating.propertyHeating,
          propertyParking: response.propertyParking.propertyParking,
          country: response.country,
          zipCode: response.zipCode,
          city: response.city,
          street: response.street
        });
        this.imageList = response.imageList;
      },
      error => {
        console.error(error);
        const message = {
          message: 'WRONG_PARAMETERS', action: 'OK'
        };
        this.propertyService.snackbarSubject.next(message);
        this.router.navigate(['']);
      }
    );
  }

  submit = () => {
    const data = this.propertyForm.value;
    data.imageList = this.imageList.map(image => image.id);
    if (this.propertyId > 0) {
      this.updateProperty(this.propertyId, data);
    } else {
      this.saveNewProperty(data);
    }
  };

  saveNewProperty = (data: PropertyFormDataModel) => {
    this.propertyService.createProperty(data).subscribe(
      () => {
        let message;
        if (this.accountInfo.role === 'ROLE_OWNER') {
          message = {
            message: 'PROPERTY_SAVED_MESSAGE_SENT', action: 'OK'
          };
        } else if (this.accountInfo.role === 'ROLE_ADMIN') {
          message = {
            message: 'PROPERTY_SAVED', action: 'OK'
          };
        }
        this.propertyService.snackbarSubject.next(message);
      },
      (error) => {
        this.handleError(error);
      },
      () => {
        this.router.navigate(["property-list"]);
      }
    );
  }

  updateProperty = (id: number, data: PropertyFormDataModel) => {
    this.propertyService.updateProperty(id, data).subscribe(
      () => {
        let message;
        if (this.accountInfo.role === 'ROLE_OWNER') {
          message = {
            message: 'PROPERTY_UPDATED', action: 'OK'
          }
        } else {
          message = {
            message: 'PROPERTY_UPDATED_BY_ADMIN', action: 'OK'
          }
        }
        this.propertyService.snackbarSubject.next(message);
      },
      (error) => {
        this.handleError(error);
      },
      () => {
        this.router.navigate(['property-details/' + id]);
      }
    );
  }

  private handleError(error) {
    const response = validationHandler(error, this.propertyForm);
    if (response === true) {
      this._snackBar.openFromComponent(SnackBarComponent, {
        data: {id: 'ACCESS_DENIED'},
        duration: 3500,
        panelClass: ['red-snackbar'],
      });
      /*this._snackBar.open('Access denied.', 'OK', {
        duration: 3500,
        panelClass: ['red-snackbar']
      });*/
    }
  }

  imageListChanged(imageList: ImageModel[]): void {
    this.imageList = imageList;
  }

  onCancel(): void {
    this.router.navigate(['property-details/' + this.propertyId]);
  }

  numberFieldCheck(field: string): void {
    this.propertyForm.patchValue({
      [field]: this.propertyForm.value[field].replace(/[^0-9]*/g, "")
    });
  }
}
