import { Component, OnInit } from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material';

@Component({
  selector: 'app-bithday-congratulation',
  templateUrl: './bithday-congratulation.component.html',
  styleUrls: ['./bithday-congratulation.component.sass']
})
export class BithdayCongratulationComponent implements OnInit {

  text: string;



  constructor() { }

  ngOnInit() {
  }

}
