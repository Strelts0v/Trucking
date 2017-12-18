import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { MatDialog } from '@angular/material';

import { BithdayCongratulationComponent } from '../bithday-congratulation/bithday-congratulation.component';
import { AuthService, RoleGuard, User } from '../users';

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
    ]),
    trigger('slideOut', [
      transition('void => *', [
        style({
          width: '0',
          opacity: 0
        }),
        animate('0.35s ease-out')
      ]),
      transition('* => void', [
        animate('0.25s ease-in', style({
          width: '0',
          opacity: 0
        }))
      ])
    ]),
    trigger('opacityIn', [
      transition('void => *', [
        style({
          opacity: 0
        }),
        animate('0.3s 0.4s ease')
      ])
    ])
  ]
})

export class MainComponent implements OnInit {
  title: string;
  subtitle: string;
  user: User;

  matches: boolean;
  navLinks = [];

  isSearchOpen: boolean;
  showHiMsg = true;

  path: string;

  constructor(private titleService: Title,
              private router: Router,
              private route: ActivatedRoute,
              private dialog: MatDialog,
              private roleGuard: RoleGuard) {

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

      this.setSearchPath();
    } else {
      this.titleService.setTitle(this.title);
      this.subtitle = '';
    }
  }

  hiMessage(): string {
    return `Нi, ${this.user.firstName}! You're logged in as ${this.user.roles.join(', ')}`;
  }

  openCloseSearch(): void {
    if (this.isSearchOpen) {
      this.isSearchOpen = false;
      setTimeout(() => {
        this.showHiMsg = true;
      }, 400);
    } else {
      this.isSearchOpen = true;
      this.showHiMsg = false;
    }
  }

  setSearchPath(): void {
    this.path = this.route.firstChild.snapshot.routeConfig.path;
    if (this.isSearchOpen) {
      this.openCloseSearch();
    }
  }

  openTemplate(): void {
    const dialogRef = this.dialog.open(BithdayCongratulationComponent);
  }

  logout() {
    AuthService.logout();
    this.router.navigate(['/auth']);
  }

  templateAvailability(): boolean {
    return this.roleGuard.isOwner() || this.roleGuard.isSysAdmin() || this.roleGuard.isAdmin();
  }

  populateLinks(): void {
    this.navLinks = [
      {
        label: 'Users',
        path: '/users',
        availability: this.roleGuard.isOwner() || this.roleGuard.isSysAdmin() || this.roleGuard.isAdmin()
      },
      {
        label: 'Clients',
        path: '/clients',
        availability: this.roleGuard.isOwner() || this.roleGuard.isSysAdmin() || this.roleGuard.isAdmin()
      },
      {
        label: 'Consignment notes',
        path: '/invoices',
        availability: this.roleGuard.isOwner() || this.roleGuard.isDispatcher() || this.roleGuard.isManager()
      },
      {
        label: 'Waybills',
        path: '/waybills',
        availability: this.roleGuard.isOwner() || this.roleGuard.isManager() || this.roleGuard.isDriver()
      },
      {
        label: 'Warehouses',
        path: '/warehouses',
        availability: this.roleGuard.isOwner() || this.roleGuard.isManager() || this.roleGuard.isDispatcher()
      }
    ];
  }

  ngOnInit(): void {
    this.onResize();

    this.setTitle();
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.setTitle();
      }
    });

    this.populateLinks();
  }
}
