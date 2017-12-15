import { Component, OnInit } from '@angular/core';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-colors',
  templateUrl: './colors.component.html',
  styleUrls: ['./colors.component.sass']
})


export class ColorsComponent implements OnInit {

  constructor() { }
  ngOnInit() {
  }

  dataSource = new MatTableDataSource<Element>(ELEMENT_DATA);
  displayedColumns = ['color']

  getColor(color){
    switch (color) {
      case 'PaleGreen':
        return 'PaleGreen';
      case 'DarkOrchid':
        return 'DarkOrchid';
      case 'LightBlue':
        return 'LightBlue';
    }
  }


}

export interface Element {
  name:string;
  color :string;
}

const ELEMENT_DATA: Element[] = [
  {name:'PaleGreen',color:'PaleGreen'},
  {name:'DarkOrchid',color:'DarkOrchid'},
  {name:'LightBlue',color:'LightBlue'},
  {name:'Thistle',color:'Thistle'},
  {name:'PaleGoldenRod',color:'PaleGoldenRod'},
  {name:'Salmon',color:'Salmon'},
  {name:'Tomato',color:'Tomato'},
  {name:'DarkSeaGreen',color:'DarkSeaGreen'},
  {name:'MediumSpringGreen',color:'MediumSpringGreen'},
  {name:'DodgerBlue',color:'DodgerBlue'},
  {name:'MistyRose',color:'MistyRose'},

];
