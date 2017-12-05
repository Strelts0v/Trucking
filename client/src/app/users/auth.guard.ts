import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';

@Injectable()
export class AuthGuard implements CanActivate {

<<<<<<< HEAD
    constructor(private router: Router) {
      localStorage.removeItem('currentUser');
    }

    canActivate() {
      this.log(localStorage.getItem('currentUser'));
      if (localStorage.getItem('currentUser')) {
        this.log('User is singed in');
        // logged in so return true
        return true;
      }

      // not logged in so redirect to login page
      this.log('User isn\'t singed in');
      this.router.navigate(['/auth']);
      return false;
    }

    private log(message: string) {
      console.log('AuthGuard: ' + message);
    }
=======
  constructor(private router: Router) {
    localStorage.removeItem('currentUser');
  }

  canActivate() {
    if (localStorage.getItem('currentUser')) {
      // logged in so return true
      return true;
    }

    // not logged in so redirect to login page
    this.router.navigate(['/auth']);
    return false;
  }
>>>>>>> 2779a581ca7999a816d6c9605d20e4eab9a989f1
}
