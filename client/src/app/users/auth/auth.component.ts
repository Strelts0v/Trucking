import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatInputModule } from '@angular/material/input';

import { AuthService } from '../index';

@Component({
    moduleId: module.id,
    selector: 'auth',
    templateUrl: 'auth.component.html',
    styleUrls: ['auth.component.sass']
})

export class AuthComponent implements OnInit {
    model: any = {};
    loading = false;
    error = '';
    
    emailFormControl = new FormControl('', [
        Validators.required,
        Validators.email,
    ]);
    
    passwordFormControl = new FormControl('', [
        Validators.required,
    ]);

    constructor(
        private router: Router,
        private authService: AuthService) { }

    ngOnInit() {
        // reset login status
        this.authService.logout();
    }

    login() {
        this.loading = true;
        this.authService.login(this.model.username, this.model.password)
            .subscribe(result => {
                if (result === true) {
                    this.router.navigate(['/']);
                } else {
                    this.error = 'Username or password is incorrect';
                    this.loading = false;
                }
            });
    }
}
