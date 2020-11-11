import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {PropertyListFilterModel} from "../../models/propertyListFilter.model";
import {PropertyService} from "../../services/property.service";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";

@Component({
  selector: 'app-property-list-filter',
  templateUrl: './property-list-filter.component.html',
  styleUrls: ['./property-list-filter.component.css']
})
export class PropertyListFilterComponent implements OnInit {

  onlyWithPicture: boolean = false;
  cityOptions: Array<string> = [];
  filteredCityOptions: Observable<string[]>;

  filterForm = this.formBuilder.group({
    minPrice: null,
    maxPrice: null,
    minNumberOfRooms: null,
    maxNumberOfRooms: null,
    minFloorArea: null,
    maxFloorArea: null,
    minSqmPrice: null,
    maxSqmPrice: null,
    withPicture: null,
    cityControl: null
  });

  @Output() newSearchEvent = new EventEmitter<string>();
  @Input() filterParameters: PropertyListFilterModel;

  constructor(private formBuilder: FormBuilder,
              private propertyService: PropertyService) {
  }

  ngOnInit() {
    this.getCities();
    if (this.filterParameters) {
      this.filterForm.patchValue(this.filterParameters);
    }
    this.filteredCityOptions = this.filterForm.get('cityControl').valueChanges
      .pipe(
        startWith(''),
        map(value => this._filter(value))
      );
  }

  getCities() {
    this.propertyService.getCities('').subscribe(
      cities => {
        this.cityOptions = cities
      },
      error => console.warn(error),
    );
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();
    return this.cityOptions.filter(option => option.toLowerCase().includes(filterValue));
  }

  submitFilter() {
    let data = this.filterForm.value;
    PropertyListFilterComponent.checkPriceRange(data);
    PropertyListFilterComponent.checkNumberOfRoomsRange(data);
    PropertyListFilterComponent.checkFloorAreaRange(data);
    PropertyListFilterComponent.checkSqmPriceRange(data);
    this.newSearchEvent.emit(data);
  }

  private static checkNullParameters(min: number, max: number) {
    return (min !== null && max !== null);
  }

  private static checkPriceRange(data) {
    if (this.checkNullParameters(data.minPrice, data.maxPrice)) {
      if (data.minPrice > data.maxPrice) {
        let tmp = data.minPrice;
        data.minPrice = data.maxPrice;
        data.maxPrice = tmp;
      }
    }
  }

  private static checkNumberOfRoomsRange(data) {
    if (this.checkNullParameters(data.minNumberOfRooms, data.maxNumberOfRooms)) {
      if (data.minNumberOfRooms > data.maxNumberOfRooms) {
        let tmp = data.minNumberOfRooms;
        data.minNumberOfRooms = data.maxNumberOfRooms;
        data.maxNumberOfRooms = tmp;
      }
    }
  }

  private static checkFloorAreaRange(data) {
    if (this.checkNullParameters(data.minFloorArea, data.maxFloorArea)) {
      if (data.minFloorArea > data.maxFloorArea) {
        let tmp = data.minFloorArea;
        data.minFloorArea = data.maxFloorArea;
        data.maxFloorArea = tmp;
      }
    }
  }

  private static checkSqmPriceRange(data) {
    if (this.checkNullParameters(data.minSqmPrice, data.maxSqmPrice)) {
      if (data.minSqmPrice > data.maxSqmPrice) {
        let tmp = data.minSqmPrice;
        data.minSqmPrice = data.maxSqmPrice;
        data.maxSqmPrice = tmp;
      }
    }
  }
}
