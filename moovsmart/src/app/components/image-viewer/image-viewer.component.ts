import {Component, Input, OnInit} from '@angular/core';
import {ImageModel} from "../../models/image.model";

@Component({
  selector: 'app-image-viewer',
  templateUrl: './image-viewer.component.html',
  styleUrls: ['./image-viewer.component.css']
})
export class ImageViewerComponent implements OnInit {

  selectedImage: ImageModel;

  _imageList: ImageModel[];

  get imageList(): ImageModel[] {
    return this._imageList;
  }

  @Input()
  set imageList(imageList: ImageModel[]) {
    this._imageList = imageList;
    if (imageList && imageList.length > 0) {
      this.selectedImage = imageList[0];
    }
  }


  constructor() {
  }

  ngOnInit() {
  }

  selectImage(image: ImageModel): void {
    this.selectedImage = image;
  }

}
