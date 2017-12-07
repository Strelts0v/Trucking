import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.sass'],
})

export class MainComponent implements OnInit {
  title = 'Trucking';
  subtitle = 'Users';

  matches: boolean;
  navLinks = [
    {label: 'Users', path: 'users'},
    {label: 'Consignment notes', path: 'invoices'},
    {label: 'Waybills', path: 'waybills'}
  ];

  constructor(private route: ActivatedRoute) {
  }

  @HostListener('window:resize')
  onResize() {
    this.matches = window.innerWidth <= 600;
  }

  ngOnInit() {
    this.matches = window.innerWidth <= 600;
  }
}
