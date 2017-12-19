import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {MatDialog} from '@angular/material';
import {LetterService} from './letter.service';
import {Letter} from './letter';
import {Image} from './image';
import {ImageService} from './image.service';


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
  image: string;

  @ViewChild('myTextArea') textarea: ElementRef;

  fullImagePath = '/assets/bday.png';

  caretPos = 0;

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
      .subscribe(data => {
        this.image = <string> data;
      });

  }

  setData() {
    this.letterService.updateLetter(this.letter).subscribe();
  }


  insertName() {
    const first = this.textarea.nativeElement.value.slice(0, this.caretPos + 1);
    const last = this.textarea.nativeElement.value.slice(this.caretPos, this.textarea.nativeElement.value.length);
    const result = first + ' ' + this.nameTag + ' ' + last;
    console.log(result);
    this.letter.text = result;

  }

  insertAge() {

    const first = this.textarea.nativeElement.value.slice(0, this.caretPos + 1);
    const last = this.textarea.nativeElement.value.slice(this.caretPos, this.textarea.nativeElement.value.length);
    const result = first + this.ageTag + last;
    console.log(result);
    this.letter.text = result;
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

];
