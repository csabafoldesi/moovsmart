import {Component, OnInit} from '@angular/core';
import {environment} from "../../../environments/environment";
import {FormBuilder} from "@angular/forms";
import {PropertyService} from "../../services/property.service";
import {BatchLoadResultModel} from "../../models/batchLoadResult.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-batch-upload',
  templateUrl: './batch-upload.component.html',
  styleUrls: ['./batch-upload.component.css']
})
export class BatchUploadComponent implements OnInit {

  baseUrl = environment.BASE_URL + '/api/properties/';
  uploadForm;
  downloadForm;
  isLoading = false;
  uploadResult: BatchLoadResultModel = null;

  constructor(private formBuilder: FormBuilder, private propertyService: PropertyService, private router: Router) {
    this.uploadForm = this.formBuilder.group({
      file: [null]
    });
    this.downloadForm = this.formBuilder.group({
      format: ['xlsx']
    });
  }

  ngOnInit() {
  }

  onUploadSubmit(): void {
    if (this.uploadForm.value.file && this.uploadForm.value.file._files && this.uploadForm.value.file._files.length === 1) {
      const file = this.uploadForm.value.file._files[0];
      this.propertyService.uploadBatchUploadFile(file).subscribe(
        response => {
          this.uploadResult = response;
        },
        error => {
          this.uploadResult = null;
          if (error.status === 401) {
            this.router.navigate(['login-form']);
          }
          console.error(error);
        },
        () => {
          this.uploadForm.patchValue({file: [null]});
        }
      );
    }
  }

}
