import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {AccountService} from "../../services/account.service";
import {AuthenticatedAccountModel} from "../../models/authenticatedAccount.model";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator, PageEvent} from "@angular/material/paginator";
import {PropertyService} from "../../services/property.service";
import {PropertyListItemModel} from "../../models/propertyListItem.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Sort} from "@angular/material/sort";
import {MatTabChangeEvent} from "@angular/material/tabs";
import {ConfirmDialogComponent} from "../confirm-dialog/confirm-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {AccountStatisticsModel} from "../../models/accountStatistics.model";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

interface SortOptions {
  value: string;
}

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {

  page: number = 0;
  pageSizeOptions: number[] = [5, 10, 25, 50, 100];
  pageSize: number = this.pageSizeOptions[0];

  sortOptions: SortOptions[] = [
    {value: 'id'},
    {value: 'id.desc'},
    {value: 'name'},
    {value: 'name.desc'},
    {value: 'country'},
    {value: 'country.desc'},
    {value: 'numberOfRooms'},
    {value: 'numberOfRooms.desc'},
    {value: 'price'},
    {value: 'price.desc'},
    {value: 'floorArea'},
    {value: 'floorArea.desc'},
    {value: 'visits'},
    {value: 'visits.desc'},
  ];
  sortBy: string = this.sortOptions[1].value;

  accountInfo: AuthenticatedAccountModel;
  pendingPropertyList: Array<PropertyListItemModel> = [];
  activePropertyList: Array<PropertyListItemModel> = [];
  inactivePropertyList: Array<PropertyListItemModel> = [];
  lengthPending: number = 0;
  lengthActive: number = 0;
  lengthInactive: number = 0;
  accountStatistics: AccountStatisticsModel;

  displayedColumnsPending: string[] = ['id', 'imageUrl', 'name', 'address', 'numberOfRooms', 'price', 'floorArea', 'activation'];
  displayedColumns: string[] = ['id', 'imageUrl', 'name', 'address', 'numberOfRooms', 'price', 'floorArea', 'views', 'activation'];
  dataSourcePending;
  dataSourceActive;
  dataSourceInactive;
  selectedTabIndex: number = 0;

  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;


  constructor(private router: Router,
              private accountService: AccountService,
              private propertyService: PropertyService,
              private _snackBar: MatSnackBar,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.setPreviousPageDetails();
    if (!localStorage.getItem('user')) {
      this.router.navigate(['/login-form']);
    }
    this.getPendingPropertiesOnePage();
    this.getActivePropertiesOnePage();
    this.getInactivePropertiesOnePage();
    this.getAccountStatistics();

    this.accountService.accountInfo().subscribe(
      (response) => {
        this.accountInfo = response;
      }, () => {
      },
      () => {
        if (this.accountInfo.role !== 'ROLE_ADMIN') {
          this.router.navigate(['/property-list']);
        }
      }
    );
  }

  onTabChanged($event: MatTabChangeEvent) {
    this.page = 0;
    this.ngOnInit();
  }

  setPreviousPageDetails() {
    if (this.propertyService.backToAdminDashboard) {
      this.page = this.propertyService.adminCurrentPage;
      this.pageSize = this.propertyService.adminPageSize;
      this.sortBy = this.propertyService.adminSorting;
      this.selectedTabIndex = this.propertyService.adminSelectedTabIndex;
      this.propertyService.redirectedBackToAdminDashboard();
    }
  }

  private getPendingPropertiesOnePage() {
    this.propertyService.getPendingProperties(this.page, this.pageSize, this.sortBy).subscribe(
      propertyListItems => {
        this.lengthPending = propertyListItems.totalItems;
        this.pendingPropertyList = propertyListItems.propertyListItems;
        this.dataSourcePending = new MatTableDataSource<any>(this.pendingPropertyList);
      }
    );
  }

  private getActivePropertiesOnePage() {
    this.propertyService.getActiveProperties(this.page, this.pageSize, this.sortBy).subscribe(
      propertyListItems => {
        this.lengthActive = propertyListItems.totalItems;
        this.activePropertyList = propertyListItems.propertyListItems;
        this.dataSourceActive = new MatTableDataSource<any>(this.activePropertyList);
      }
    );
  }

  private getInactivePropertiesOnePage() {
    this.propertyService.getInactiveProperties(this.page, this.pageSize, this.sortBy).subscribe(
      propertyListItems => {
        this.lengthInactive = propertyListItems.totalItems;
        this.inactivePropertyList = propertyListItems.propertyListItems;
        this.dataSourceInactive = new MatTableDataSource<any>(this.inactivePropertyList);
      }
    );
  }

  private getAccountStatistics() {
    this.accountService.getAccountStatistics().subscribe(
      (response) => {
        this.accountStatistics = response;
      }
    );
  }

  propertyDetails(id: any) {
    this.propertyService.addAdminPageDetails(this.page, this.pageSize, this.sortBy, this.selectedTabIndex);
    this.propertyService.detailsPageVisitedFromAdminDashboard();
    this.router.navigate(['property-details', id]);
  }

  activateProperty(propertyId: any) {
    this.propertyService.activateProperty(propertyId).subscribe(
      () => {
        this.getPendingPropertiesOnePage();
        this.getActivePropertiesOnePage();
        this.getInactivePropertiesOnePage();

        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'PROPERTY_ACTIVATED'},
          duration: 5000,
          panelClass: ['green-snackbar'],
        });
        /*this._snackBar.open('Property advertisement has been activated. Message has been sent to owner.', 'OK', {
          duration: 5000,
          panelClass: ['green-snackbar']
        });*/
      },
      () => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'PROPERTY_ACTIVATION_ERROR'},
          duration: 5000,
          panelClass: ['red-snackbar'],
        });
        /*this._snackBar.open('Error during activation. Account type may have changed to visitor.', 'OK', {
          duration: 5000,
          panelClass: ['red-snackbar']
        });*/
      }
    );
  }

  openDialog(propertyId: any): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      data: {
        title: 'INACTIVATION',
        content: 'INACTIVATE_PROPERTY',
        button: 'INACTIVATE',
      },
      width: '350px',
      backdropClass: 'dialog-custom-color',
      autoFocus: false
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.inactivateProperty(propertyId);
      }
    });
  }

  inactivateProperty(propertyId: any) {
    this.propertyService.inactivateProperty(propertyId).subscribe(
      () => {
        this.getPendingPropertiesOnePage();
        this.getActivePropertiesOnePage();
        this.getInactivePropertiesOnePage();

        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'PROPERTY_INACTIVATED'},
          duration: 5000,
          panelClass: ['green-snackbar'],
        });
        /*this._snackBar.open('Property advertisement has been inactivated. Message has been sent to owner.', 'OK', {
          duration: 5000,
          panelClass: ['green-snackbar']
        });*/
      },
      () => {
        this._snackBar.openFromComponent(SnackBarComponent, {
          data: {id: 'PROPERTY_INACTIVATION_ERROR'},
          duration: 5000,
          panelClass: ['red-snackbar'],
        });
        /*this._snackBar.open('Error during inactivation.', 'OK', {
          duration: 5000,
          panelClass: ['red-snackbar']
        });*/
      }
    );
  }

  onPageChanged($event: PageEvent) {
    this.page = $event.pageIndex;
    this.pageSize = $event.pageSize;
    if (this.selectedTabIndex === 0) {
      this.getPendingPropertiesOnePage();
    } else if (this.selectedTabIndex === 1) {
      this.getActivePropertiesOnePage();
    } else if (this.selectedTabIndex === 2) {
      this.getInactivePropertiesOnePage();
    }
  }

  sortData($event: Sort) {
    if (!$event.active || $event.direction === '') {
      this.sortBy = this.sortOptions[1].value;
    } else if ($event.direction === 'asc') {
      if ($event.active.toString() === 'id') {
        this.sortBy = this.sortOptions[0].value;
      } else if ($event.active.toString() === 'name') {
        this.sortBy = this.sortOptions[2].value;
      } else if ($event.active.toString() === 'address') {
        this.sortBy = this.sortOptions[4].value;
      } else if ($event.active.toString() === 'numberOfRooms') {
        this.sortBy = this.sortOptions[6].value;
      } else if ($event.active.toString() === 'price') {
        this.sortBy = this.sortOptions[8].value;
      } else if ($event.active.toString() === 'floorArea') {
        this.sortBy = this.sortOptions[10].value;
      } else if ($event.active.toString() === 'views') {
        this.sortBy = this.sortOptions[12].value;
      }
    } else if ($event.direction === 'desc') {
      if ($event.active.toString() === 'id') {
        this.sortBy = this.sortOptions[1].value;
      } else if ($event.active.toString() === 'name') {
        this.sortBy = this.sortOptions[3].value;
      } else if ($event.active.toString() === 'address') {
        this.sortBy = this.sortOptions[5].value;
      } else if ($event.active.toString() === 'numberOfRooms') {
        this.sortBy = this.sortOptions[7].value;
      } else if ($event.active.toString() === 'price') {
        this.sortBy = this.sortOptions[9].value;
      } else if ($event.active.toString() === 'floorArea') {
        this.sortBy = this.sortOptions[11].value;
      } else if ($event.active.toString() === 'views') {
        this.sortBy = this.sortOptions[13].value;
      }
    }
    if (this.selectedTabIndex === 0) {
      this.getPendingPropertiesOnePage();
    } else if (this.selectedTabIndex === 1) {
      this.getActivePropertiesOnePage();
    } else if (this.selectedTabIndex === 2) {
      this.getInactivePropertiesOnePage();
    }
  }
}



