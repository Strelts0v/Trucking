import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-new-button',
  templateUrl: './new-button.component.html',
  styleUrls: ['./new-button.component.sass']
})
export class NewButtonComponent implements OnInit {

  @Output() _click: EventEmitter<any> = new EventEmitter();

  constructor() { }

  onClick() {
    this._click.emit(null);
  }

  ngOnInit() {
  }

}
