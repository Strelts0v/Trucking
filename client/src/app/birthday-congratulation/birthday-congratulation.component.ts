import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatTableDataSource,} from '@angular/material';
import {LetterService} from './letter.service';
import {Letter} from './letter';


@Component({
  selector: 'app-bithday-congratulation',
  templateUrl: './birthday-congratulation.component.html',
  styleUrls: ['./birthday-congratulation.component.sass'],


})
export class BirthdayCongratulationComponent implements OnInit {

  nameTag: string = '${fullname}';
  ageTag: string = '${age}';
  colors = COLORS ;
  currentText :string;
  currentColor : string;

  @Input()
  letter : Letter;

  @ViewChild('myTextArea') textarea: ElementRef;

  fullImagePath: string = '/assets/bday.png';

  caretPos: number = 0;

  constructor(private element: ElementRef,
              private dialog: MatDialog,
              private letterService : LetterService) {
  }

  ngOnInit() {
    //this.letter = this.birthday_service.getLetter();
    this.letter = new Letter();
    this.getData();
    console.log(this.letter);

   // this.letter.text = "Поздравляем с ДР";
   // this.letter.color = "MediumSpringGreen";
    this.currentColor = this.letter.color;
    this.currentText = this.letter.text;

  }

  ngOnChanges() {
    console.log('Changed value in componnt');

  }
  getData() {

    this.letterService.getLetter().subscribe(data => this.letter = data);
    console.log(this.letter.color);
    console.log(this.letter.text);
  }

  setData() {

    this.letterService.updateLetter(this.letter).subscribe(data => this.letter = data);
    this.letter.text =  this.currentText;
    this.letter.color = this.currentColor;

  }


  insertName() {


    const first = this.textarea.nativeElement.value.slice(0, this.caretPos + 1);
    const last = this.textarea.nativeElement.value.slice(this.caretPos, this.textarea.nativeElement.value.length);
    const result = first + ' ' + this.nameTag + ' ' + last;
    console.log(result);
    this.currentText = result;

  }

  insertAge() {

    const first = this.textarea.nativeElement.value.slice(0, this.caretPos + 1);
    const last = this.textarea.nativeElement.value.slice(this.caretPos, this.textarea.nativeElement.value.length);
    const result = first + this.ageTag + last;
    console.log(result);
    this.currentText = result;
  }


  getCaretPos(oField) {
    if (oField.selectionStart || oField.selectionStart == '0') {
      this.caretPos = oField.selectionStart;
    }
  }

}

export interface Color {

  name :string;
}

const COLORS: Color[] = [
  {name:'PaleGreen'},
  {name:'DarkOrchid'},
  {name:'LightBlue'},
  {name:'Thistle'},
  {name:'PaleGoldenRod'},
  {name:'Salmon'},
  {name:'Tomato'},
  {name:'DarkSeaGreen'},
  {name:'MediumSpringGreen'},
  {name:'DodgerBlue'},
  {name:'MistyRose'},

];
