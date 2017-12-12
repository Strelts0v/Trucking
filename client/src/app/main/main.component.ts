import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { MatDialog } from '@angular/material';
import { BithdayCongratulationComponent } from '../bithday-congratulation/bithday-congratulation.component';
import { AuthService } from '../users';
import { User } from '../users/index';

@Component({
  moduleId: module.id,
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.sass'],
  animations: [
    trigger('flyInFromLeft', [
      state('in', style({opacity: 1, transform: 'translateX(0)'})),
      transition('void => *', [
        style({
          opacity: 0,
          transform: 'translateX(-100%)'
        }),
        animate('0.3s ease-in')
      ])
    ]),
    trigger('flyInFromTop', [
      state('in', style({opacity: 1, transform: 'translateY(0)'})),
      transition('void => *', [
        style({
          opacity: 0,
          transform: 'translateY(-100%)'
        }),
        animate('0.3s ease-in')
      ])
    ])
  ]
})

export class MainComponent implements OnInit {
  title: string;
  subtitle: string;
  user: User;

  matches: boolean;
  navLinks = [
    {label: 'Users', path: '/users'},
    {label: 'Consignment notes', path: '/invoices'},
    {label: 'Waybills', path: '/waybills'},
    {label: 'Clients', path: '/clients'},
  ];

  constructor(private titleService: Title,
              private router: Router,
              private route: ActivatedRoute,
              private dialog: MatDialog) {
    if (localStorage.getItem('currentUser')) {
      this.user = JSON.parse(localStorage.getItem('currentUser'));
    } else {
      this.user = new User();
    }
  }

  @HostListener('window:resize')
  onResize() {
    this.matches = window.innerWidth <= 600;
  }

  setTitle() {
    this.title = this.route.snapshot.data.title;
    if (this.route.firstChild) {
      this.subtitle = this.route.firstChild.snapshot.data.title;
      this.titleService.setTitle(`${this.subtitle} - ${this.title}`);
    } else {
      this.titleService.setTitle(this.title);
      this.subtitle = '';
    }
  }

  openTemplate() {
    const dialogRef = this.dialog.open(BithdayCongratulationComponent);
  }

  logout() {
    AuthService.logout();
    this.router.navigate(['/auth']);
  }

  ngOnInit() {
    this.onResize();

    this.setTitle();
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.setTitle();
      }
    });
  }
}
