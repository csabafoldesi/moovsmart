import {Component, OnInit} from '@angular/core';
import {PropertyListItemModel} from "../../models/propertyListItem.model";
import {Router} from "@angular/router";
import {PageEvent} from "@angular/material/paginator";
import {BookmarkService} from "../../services/bookmark.service";
import {AccountService} from "../../services/account.service";
import {AuthenticatedAccountModel} from "../../models/authenticatedAccount.model";

@Component({
  selector: 'app-bookmark-list',
  templateUrl: './bookmark-list.component.html',
  styleUrls: ['./bookmark-list.component.css']
})
export class BookmarkListComponent implements OnInit {

  pageLength: number;
  page: number = 0;
  pageSizeOptions: number[] = [5, 10, 25, 50, 100];
  pageSize: number = this.pageSizeOptions[0];
  sortBy: string = 'id.desc';

  bookmarkedProperties: Array<PropertyListItemModel> = [];
  displayedColumns: string[] = ['imageUrl', 'name', 'numberOfRooms', 'price', 'floorArea', 'delete'];

  accountInfo: AuthenticatedAccountModel;

  constructor(private bookmarkService: BookmarkService,
              private accountService: AccountService,
              private router: Router) {
  }

  ngOnInit() {
    if (!localStorage.getItem('user')) {
      this.router.navigate(['/login-form']);
    }
    this.getAccountInfo();
  }

  getMyBookmarks() {
    this.bookmarkService.getMyBookmarks().subscribe(
      bookmarkedPropertyList => {
        this.bookmarkedProperties = bookmarkedPropertyList;
      }
    );
  }

  propertyDetails(id: any) {
    this.router.navigate(['property-details', id]);
  }

  onPageChanged($event: PageEvent) {
    this.page = $event.pageIndex;
    this.pageSize = $event.pageSize;
  }


  deleteBookmark(id: number) {
    let bookmarkData = {
      accountEmail: this.accountInfo.emailAddress,
      propertyId: id
    };
    this.bookmarkService.deleteBookmark(bookmarkData).subscribe(
      () => this.getMyBookmarks()
    );
  }

  private getAccountInfo(): void {
    this.accountService.accountInfo().subscribe(
      (response) => {
        this.accountInfo = response;
        this.getMyBookmarks();
      },
      () => {
      },
    );
  }

}
