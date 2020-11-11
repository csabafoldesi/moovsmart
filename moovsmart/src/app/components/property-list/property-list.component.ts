import {Component, OnDestroy, OnInit} from '@angular/core';
import {PropertyService} from "../../services/property.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PropertyListItemModel} from "../../models/propertyListItem.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {PageEvent} from "@angular/material/paginator";
import {PropertyListFilterModel} from "../../models/propertyListFilter.model";
import {MatDialog} from "@angular/material/dialog";
import {LoadingPopupComponent} from "../loading-popup/loading-popup.component";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

interface Sort {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-property-list',
  templateUrl: './property-list.component.html',
  styleUrls: ['./property-list.component.css']
})
export class PropertyListComponent implements OnInit, OnDestroy {

  propertyList: Array<PropertyListItemModel> = [];
  snackBarSubscription;

  pageLength: number;
  page: number = 0;
  pageSizeOptions: number[] = [10, 50, 100];
  pageSize: number = this.pageSizeOptions[0];

  pageViewStyle = 'view-image';

  sortOptions: Sort[] = [
    {value: 'id.desc', viewValue: 'Default'},
    {value: 'price', viewValue: 'Lowest price'},
    {value: 'price.desc', viewValue: 'Highest price'},
  ];
  sortBy: string = this.sortOptions[0].value;
  searchParameters: PropertyListFilterModel;

  isLoading = false;

  constructor(private propertyService: PropertyService,
              private router: Router,
              private _snackBar: MatSnackBar,
              private activatedRoute: ActivatedRoute,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    if (localStorage.getItem('pageViewStyle')) {
      this.pageViewStyle = localStorage.getItem('pageViewStyle');
    }
    this.setPreviousPageDetails();
    this.activatedRoute.paramMap.subscribe(
      paramMap => {
        this.page = +paramMap.get('page') - 1;
        if (isNaN(this.page) || this.page < 0) {
          this.page = 0;
          this.router.navigate(['property-list', this.page + 1]);
        }
        this.getActivePropertiesOnePage();
      },
      error => {
        this.propertyList = [];
        this.pageLength = 0;
      },
    );

    this.snackBarSubscription = this.propertyService.snackbarSubject.subscribe(
      (response) => {
        if (response !== null) {
          this.showSnackBar(response);
          this.propertyService.snackbarSubject.next(null);
        }
      }
    );
  }

  setPreviousPageDetails() {
    if (this.propertyService.backFromDetailsPage) {
      this.page = this.propertyService.myCurrentPage;
      this.pageSize = this.propertyService.myPageSize;
      this.sortBy = this.propertyService.mySorting;
      this.searchParameters = this.propertyService.mySearchParameter;
    }
  }

  ngOnDestroy() {
    if (this.snackBarSubscription) {
      this.snackBarSubscription.unsubscribe();
    }
  }

  details(id: number) {
    this.propertyService.addPageDetails(this.page, this.pageSize, this.sortBy, this.searchParameters);
    this.router.navigate(['property-details', id]);
  }

  private showSnackBar(response) {
    if (response.message === 'WRONG_PROPERTY_ID') {
      this._snackBar.openFromComponent(SnackBarComponent, {
        data: {id: 'WRONG_PROPERTY_ID'},
        duration: 10000,
        panelClass: ['red-snackbar'],
      });
    } else {
      this._snackBar.openFromComponent(SnackBarComponent, {
        data: {id: response.message},
        duration: 10000,
        panelClass: ['green-snackbar'],
      });
      /*this._snackBar.open(response.message, response.action, {
        duration: 10000,
        panelClass: ['green-snackbar']
      });*/
    }
  }

  OnPageChange($event: PageEvent) {
    this.page = $event.pageIndex;
    this.pageSize = $event.pageSize;
    this.getActivePropertiesOnePage();
    this.router.navigate(['property-list', this.page + 1]);
  }

  getActivePropertiesOnePage() {
    this.openLoadingDialog();
    this.propertyService.getActivePropertiesWithFilter(this.page, this.pageSize, this.sortBy, this.searchParameters).subscribe(
      propertyListItems => {
        console.log(propertyListItems);
        this.pageLength = propertyListItems.totalItems;
        if (this.page > this.pageLength / this.pageSize) {
          this.page = 0;
          this.router.navigate(['property-list', this.page + 1]);
        }
        this.propertyList = propertyListItems.propertyListItems;
        this.closeLoadingDialog();
      },
      error => {
        this.propertyList = []
        this.pageLength = 0;
        this.closeLoadingDialog();
      }
    );
  }

  newSearch(search) {
    this.searchParameters = search;
    this.getActivePropertiesOnePage();
  }

  viewSelectionChanged(event: any): void {
    this.pageViewStyle = event.value;
    localStorage.setItem('pageViewStyle', this.pageViewStyle);
  }

  openLoadingDialog(): void {
    this.isLoading = true;
    this.dialog.open(LoadingPopupComponent, {disableClose: true});
  }

  closeLoadingDialog(): void {
    this.isLoading = false;
    this.dialog.closeAll();
  }

}
