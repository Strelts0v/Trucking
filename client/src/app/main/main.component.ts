import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { AuthService } from './../users/index';

@Component({
  moduleId: module.id,
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.sass'],
})

export class MainComponent implements OnInit {
  title: string;
  subtitle: string;

  matches: boolean;
  navLinks = [
    {label: 'Users', path: '/users'},
    {label: 'Consignment notes', path: '/invoices'},
    {label: 'Waybills', path: '/waybills'}
  ];

  constructor(private titleService: Title,
              private router: Router,
              private route: ActivatedRoute,
              private authService: AuthService) {
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

  ngOnInit() {
    this.onResize();

    this.setTitle();
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.setTitle();
      }
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/auth']);
  }
}
