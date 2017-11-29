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
    console.log(`>> Logging data: ${JSON.stringify(this.user)}`);

    this.loading = true;
    this.authService.login(this.user.username, this.user.password)
      .subscribe(result => {
        console.log('result of auth: ' + result);
        if (result === true) {
          this.router.navigate(['/']);
        } else {
          this.error = 'Username or password is incorrect';
          this.loading = false;
        }
      });
  }

  createForm() {
    this.authForm = this.fb.group({
      username: [Validators.required],
      password: [Validators.required]
    });
  }

  get username() {
    return this.authForm.get('username');
  }

  get password() {
    return this.authForm.get('password');
  }
}
