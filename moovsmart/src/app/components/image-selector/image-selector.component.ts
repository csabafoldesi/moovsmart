import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder} from "@angular/forms";
import {UploadService} from "../../services/upload.service";
import {ImageModel} from "../../models/image.model";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SnackBarComponent} from "../snack-bar/snack-bar.component";

@Component({
  selector: 'app-image-selector',
  templateUrl: './image-selector.component.html',
  styleUrls: ['./image-selector.component.css']
})
export class ImageSelectorComponent implements OnInit {

  @Output()
  imageEmitter: EventEmitter<ImageModel[]> = new EventEmitter();

  @Input()
  imageList: ImageModel[] = [];

  imageForm;
  isLoading = false;

  constructor(private formBuilder: FormBuilder,
              private uploadService: UploadService,
              private snackBar: MatSnackBar) {
    this.imageForm = this.formBuilder.group({
      image: [null]
    });
  }

  ngOnInit() {
  }

  onChange(event) {
    if (event.target.files.length > 0) {
      this.isLoading = true;
      this.imageForm.get('image').disable();
      const file = event.target.files[0];
      this.uploadService.uploadFile(file).subscribe(
        response => {
          this.imageForm.patchValue({image: null});
          this.imageList.push({
            id: response.id,
            filePath: response.filePath,
            thumbnailPath: response.thumbnailPath,
            title: response.title
          });
          this.updateParentImageList();
        },
        error => {
          this.isLoading = false;
          this.imageForm.get('image').enable();
          console.error(error);
          if (error.status === 415) {
            this.showError('UNSUPPORTED_FILE_FORMAT');
          } else {
            //this.showError(error.statusText);
            console.error(error.statusText);
          }
        },
        () => {
          this.imageForm.get('image').enable();
          this.isLoading = false;
        }
      );
    }
  }

  deleteImage(index: number): void {
    if (confirm("Do you really want to remove this image?") && (index < this.imageList.length)) {
      const id = this.imageList[index].id;
      this.uploadService.deleteFile(id).subscribe(
        response => {
          this.imageList.splice(index, 1);
          this.updateParentImageList();
        },
        error => {
          console.error(error);
        }
      );
    }
  }

  updateParentImageList(): void {
    this.imageEmitter.emit(this.imageList);
  }

  onImageTitleChanged(image: ImageModel, event: any): void {
    image.title = event.target.value;
    this.uploadService.updateFileTitle(image).subscribe(
      response => {
        //console.log(response);
      },
      error => {
        console.error(error);
      }
    );
  }

  private showError(id: string): void {
    this.snackBar.openFromComponent(SnackBarComponent, {
      data: {id: id},
      duration: 3500,
      panelClass: ['red-snackbar'],
    });
  }

}
