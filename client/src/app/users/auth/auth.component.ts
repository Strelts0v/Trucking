import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../index';
import { User } from '../user';

@Component({
  moduleId: module.id,
  selector: 'app-auth',
  templateUrl: 'auth.component.html',
  styleUrls: ['auth.component.sass']
})

export class AuthComponent implements OnInit {
  authForm: FormGroup;
  user = new User();
  loading = false;
  error = '';

  constructor(private router: Router,
              private authService: AuthService,
              private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit() {
    // reset login status
    this.authService.logout();
  }

  login() {
    this.log(`Logging data: ${JSON.stringify(this.user)}`);

    this.loading = true;
    this.authService.login(this.user.email, this.user.password)
      .subscribe(data => {
        if (data['token']) {
          this.log('result of auth: ' + data['token']);
          this.router.navigate(['/']);
        } else {
          this.error = 'Email or password is incorrect';
          this.log('Incorrect password or email address');
        }
        this.loading = false;
      });
  }

  createForm() {
    this.authForm = this.fb.group({
      email: [Validators.required],
      password: [Validators.required]
    });
  }

  private log(message: string) {
    console.log('AuthComponent: ' + message);
  }

  get username() {
    return this.authForm.get('email');
  }

  get password() {
    return this.authForm.get('password');
  }
}
