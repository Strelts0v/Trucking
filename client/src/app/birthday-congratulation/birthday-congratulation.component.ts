import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatTableDataSource,} from '@angular/material';
import {BirthdayCongragulation} from './birthday-congragulation';

@Component({
  selector: 'app-bithday-congratulation',
  templateUrl: './birthday-congratulation.component.html',
  styleUrls: ['./birthday-congratulation.component.sass']

})
export class BirthdayCongratulationComponent implements OnInit {

  nameTag: string = '${fullname}';
  ageTag: string = '${age}';

  @Input()
  letter : BirthdayCongragulation = {
    text: 'Уважаемый\n' +
    ' Поздравляем Вас с\n' +
    ' С уважением, коллектив ООО ”Транспортные системы”',
    color : "MediumSpringGreen",
  };

  colors = COLORS ;

  @ViewChild('myTextArea') textarea: ElementRef;

  fullImagePath: string = '/assets/bday.png';

  caretPos: number = 0;

  constructor(private element: ElementRef,
              private dialog: MatDialog) {
  }

  ngOnInit() {
  }

  ngOnChanges() {
    console.log('Changed value in componnt');

  }

  setData() {


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
