import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) {
      localStorage.removeItem('currentUser');
    }

    canActivate() {
      this.log(localStorage.getItem('currentUser'));
      if (localStorage.getItem('currentUser')) {
        this.log('Place is singed in');
        // logged in so return true
        return true;
      }

      // not logged in so redirect to login page
      this.log('Place isn\'t singed in');
      this.router.navigate(['/auth']);
      return false;
    }

    private log(message: string) {
      console.log('AuthGuard: ' + message);
    }
}
