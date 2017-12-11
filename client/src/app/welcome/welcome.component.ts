import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry } from '@angular/material';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.sass'],
  animations: [
    trigger('zoomIn', [
      state('in', style({opacity: 1, transform: 'scale(1)'})),
      transition('void => *', [
        style({
          opacity: 0,
          transform: 'scale(0)'
        }),
        animate('0.3s 400ms ease-in')
      ])
    ]),
  ]
})
export class WelcomeComponent {

  constructor(private iconRegistry: MatIconRegistry,
              private sanitizer: DomSanitizer) {

    iconRegistry.addSvgIcon(
      'truck_fast',
      sanitizer.bypassSecurityTrustResourceUrl('assets/icon/truck-fast.svg'));
  }

}
