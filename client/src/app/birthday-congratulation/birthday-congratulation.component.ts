import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {MatDialog, MatTableDataSource,} from '@angular/material';
import {ColorsComponent} from './colors/colors.component';
import {BackgroundDirective} from './directive/directive.background';

@Component({
  selector: 'app-bithday-congratulation',
  templateUrl: './birthday-congratulation.component.html',
  styleUrls: ['./birthday-congratulation.component.sass']

})
export class BirthdayCongratulationComponent implements OnInit {

   nameTag :string = "$Fullname";
   ageTag :string = "$Age";

  text: string = 'Уважаемый\n' +
    ' Поздравляем Вас с\n' +
    ' С уважением, коллектив ООО ”Транспортные системы”';


  headerColor: string = 'MediumSpringGreen';
  //#78ab46
  @ViewChild('myTextArea') textarea: ElementRef;

  fullImagePath: string = '/assets/bday.png';

  caretPos: number = 0;

  constructor(private element: ElementRef,
              private dialog: MatDialog) {
  }

  ngOnInit() {
  }

  ngOnChanges(){
    console.log("Changed value in componnt");
    console.log(this.headerColor);
  }

  openColorsComponent(){
    const dialogRef = this.dialog.open(ColorsComponent);
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
    const result = first + " " +  this.nameTag + " " + last;
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
