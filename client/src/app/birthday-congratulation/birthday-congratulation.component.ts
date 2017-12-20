import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material';
import {LetterService} from './letter.service';
import {Letter} from './letter';
import {ImageService} from './image.service';
import {HttpEventType, HttpResponse} from '@angular/common/http';


@Component({
  selector: 'app-bithday-congratulation',
  templateUrl: './birthday-congratulation.component.html',
  styleUrls: ['./birthday-congratulation.component.sass'],
  providers: [ImageService]
})
export class BirthdayCongratulationComponent implements OnInit {

  nameTag = '${fullname}';
  ageTag = '${age}';
  colors = COLORS;
  letter: Letter;
  image = 'localhost:8080/api/image/get';

  selectedFiles: FileList;
  currentFileUpload: File;
  progress: { percentage: number } = {percentage: 0};
  @ViewChild('myTextArea') textarea: ElementRef;
  caretPos = 0;

  @ViewChild('templateImage') templateImage: ElementRef;

  constructor(private element: ElementRef,
              private dialog: MatDialog,
              private letterService: LetterService,
              private imageService: ImageService) {
  }

  ngOnInit() {

    this.letter = new Letter();
    this.getData();
  }

  getData() {

    this.letterService.getLetter()
      .subscribe(data => {
        this.letter = <Letter> data;
      });
    this.imageService.getImgage()
      .subscribe(image => {
        this.templateImage.nativeElement.src = window.URL.createObjectURL(image);
      });
  }

  setData() {
    this.letterService.updateLetter(this.letter).subscribe();
    this.upload();
  }


  selectFile(event) {
    const file = event.target.files.item(0);
    if (file.size < 500000) {
      if (file.type.match('image.*')) {
        this.selectedFiles = event.target.files;
        const reader = new FileReader();
        reader.onload = (event: any) => {
          this.templateImage.nativeElement.src = event.target.result;
        };

        reader.readAsDataURL(event.target.files[0]);
      } else {
        alert('invalid format! Please choose files only (image.*)');
      }

    } else {
      alert('This file is too large. max size is 500kb');
    }


  }


  upload() {
    this.progress.percentage = 0;

    if (this.selectedFiles.item(0)) {
      this.currentFileUpload = this.selectedFiles.item(0);
      this.imageService.pushFileToStorage(this.currentFileUpload).subscribe(event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress.percentage = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          console.log('File is completely uploaded!');
        }
      });
      this.selectedFiles = null;
    }
  }

  insertName() {
    const first = this.textarea.nativeElement.value.slice(0, this.caretPos);
    const last = this.textarea.nativeElement.value.slice(this.caretPos, this.textarea.nativeElement.value.length);
    this.letter.text = first + ' ' + this.nameTag + ' ' + last;

  }

  insertAge() {

    const first = this.textarea.nativeElement.value.slice(0, this.caretPos);
    const last = this.textarea.nativeElement.value.slice(this.caretPos, this.textarea.nativeElement.value.length);
    this.letter.text = first + ' ' + this.ageTag + ' ' + last;
  }


  getCaretPos(oField) {
    if (oField.selectionStart || oField.selectionStart === '0') {
      this.caretPos = oField.selectionStart;
    }
  }

}

export interface Color {

  name: string;
}

const COLORS: Color[] = [
  {name: 'PaleGreen'},
  {name: 'DarkOrchid'},
  {name: 'LightBlue'},
  {name: 'Thistle'},
  {name: 'PaleGoldenRod'},
  {name: 'Salmon'},
  {name: 'Tomato'},
  {name: 'DarkSeaGreen'},
  {name: 'MediumSpringGreen'},
  {name: 'DodgerBlue'},
  {name: 'MistyRose'},
  {name: 'DeepSkyBlue'},
  {name: '#228B22'},
  {name: '#F08080'},
  {name: '#E0FFFF'},
  {name: '#B0C4DE'},
  {name: '#32CD32'},
  {name: '#FFE4E1'},
  {name: '#FFA500'},
  {name: '#DA70D6'},

];
