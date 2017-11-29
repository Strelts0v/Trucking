import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

@Injectable()
export class AuthService {

  private authUrl = 'http://localhost:8080/auth';

  public token: string;

  private isAuthOk = false;

  constructor(private http: HttpClient) {
    // set token if saved in local storage
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  handleData(data, username) {
    this.token = data.token;
    if (this.token) {
      localStorage.setItem('currentUser', JSON.stringify({ username: username, token: this.token }));
      this.isAuthOk = true;
    } else {
      this.isAuthOk = false;
    }
  }

  login(email: string, password: string): Observable<boolean> {
    this.http.post(this.authUrl, {email: email, password: password})
      .subscribe(data => {
        if (data['token']) {
          this.token = data['token'];
          console.log(data);
          console.log(this.token);
          if (this.token) {
            localStorage.setItem('currentUser', JSON.stringify({ username: email, token: this.token }));
            this.isAuthOk = true;
          } else {
            this.isAuthOk = false;
          }
          console.log(`1 Is authorised: {this.isAuthOk}`);
          return of(this.isAuthOk); // just mock
        }
      });
      console.log(`2 Is authorised: {this.isAuthOk}`);
      return of(this.isAuthOk); // just mock
  }

  // login(email: string, password: string): Observable<boolean> {
  //   return this.http.post(this.authUrl, JSON.stringify({ email: email, password: password }))
  //   .map((response: Response) => {
  //       // login successful if there's a jwt token in the response
  //       const token = response.json() && response.json().token;
  //       if (token) {
  //           // set token property
  //           this.token = token;

  //           // store username and jwt token in local storage to keep user logged in between page refreshes
  //           localStorage.setItem('currentUser', JSON.stringify({ email: email, token: token }));

  //           // return true to indicate successful login
  //           return true;
  //       } else {
  //           // return false to indicate failed login
  //           return false;
  //       }
  //   });
  // }

  logout(): void {
    // clear token remove user from local storage to log user out
    this.token = null;
    localStorage.removeItem('authenticationToken');
  }

  getToken(): string {
    return this.token;
  }
}
