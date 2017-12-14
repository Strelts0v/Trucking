import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource,} from '@angular/material';

@Component({
  selector: 'app-bithday-congratulation',
  templateUrl: './bithday-congratulation.component.html',
  styleUrls: ['./bithday-congratulation.component.sass']

})
export class BithdayCongratulationComponent implements OnInit {

   nameTag :string = "$Fullname";
   ageTag :string = "$Age";

  text: string = 'Уважаемый\n' +
    ' Поздравляем Вас с\n' +
    ' С уважением, коллектив ООО ”Транспортные системы”';


  headerColor: string = '#78ab46';

  @ViewChild('myTextArea') textarea: ElementRef;

  fullImagePath: string = '/assets/bday.png';

  caretPos: number = 0;

  constructor(private element: ElementRef) {
  }

  ngOnInit() {
  }

  changeBackgroundColor() {
    console.log('clicket o background');
  }

  changeImage(event) {
    event.stopPropagation();
    console.log('clicked on image');

  }

  insertName() {


    const first = this.textarea.nativeElement.value.slice(0, this.caretPos + 1);
    const last = this.textarea.nativeElement.value.slice(this.caretPos, this.textarea.nativeElement.value.length);
    const result = first + this.nameTag + last;
    console.log(result);

    this.text = result;

  }

  insertAge() {

    const first = this.textarea.nativeElement.value.slice(0, this.caretPos + 1);
    const last = this.textarea.nativeElement.value.slice(this.caretPos, this.textarea.nativeElement.value.length);
    const result = first + this.ageTag + last;
    console.log(result);

    this.text = result;
  }


  getCaretPos(oField) {
    if (oField.selectionStart || oField.selectionStart == '0') {
      this.caretPos = oField.selectionStart;
    }
  }

}
