import { Component, OnInit } from '@angular/core';
import {MatTableDataSource,  } from '@angular/material';

@Component({
  selector: 'app-bithday-congratulation',
  templateUrl: './bithday-congratulation.component.html',
  styleUrls: ['./bithday-congratulation.component.sass']

})
export class BithdayCongratulationComponent implements OnInit {

  text: string = "Уважаемый\n" +
    " Поздравляем Вас с\n" +
    ' С уважением, коллектив ООО ”Транспортные системы”';



  constructor() { }

  ngOnInit() {
  }

}
