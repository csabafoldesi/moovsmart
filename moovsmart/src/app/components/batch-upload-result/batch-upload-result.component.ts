import {Component, Input, OnInit} from '@angular/core';
import {BatchLoadResultModel} from "../../models/batchLoadResult.model";

@Component({
  selector: 'app-batch-upload-result',
  templateUrl: './batch-upload-result.component.html',
  styleUrls: ['./batch-upload-result.component.css']
})
export class BatchUploadResultComponent implements OnInit {

  recordCount = {
    success: 0,
    unSuccess: 0
  };
  objectKeys = Object.keys;
  columnNames: string[] = [];

  constructor() {
  }

  _uploadResult: BatchLoadResultModel = null;

  get uploadResult(): BatchLoadResultModel {
    return this._uploadResult;
  }

  @Input()
  set uploadResult(item: BatchLoadResultModel) {
    this._uploadResult = item;
    this.recordCount = {
      success: 0,
      unSuccess: 0
    };
    this.columnNames = [];
    this.uploadResult.resultItems.forEach(record => {
      if (Object.keys(record.errorList).length > 0) {
        this.recordCount.unSuccess++;
      } else {
        this.recordCount.success++;
      }
    });
    if (this.uploadResult.resultItems.length > 0) {
      Object.keys(this.uploadResult.resultItems[0].propertyForm).forEach(column => {
        this.columnNames.push(column);
      });
    }
  }

  ngOnInit() {
  }

}
