import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { of } from 'rxjs/observable/of';

@Injectable()
export class AuthService {

  private authUrl = 'http://localhost:8080/auth';

  public token: string;

  constructor(private http: HttpClient) {
    // set token if saved in local storage
    const currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.token = currentUser && currentUser.token;
  }

  login(username: string, password: string): Observable<boolean> {
    /*return*/ this.http.post(this.authUrl, {username: username, password: password})
      .subscribe(
        data => console.log(data),
        error => console.error(error)
      );
    return of(false); // just mock

    /*.map((response: Response) => {
      // login successful if there's a jwt token in the response
      let token = response.json() && response.json().token;
      if (token) {
        // set token property
        this.token = token;

        // store username and jwt token in local storage to keep user logged in between page refreshes
        localStorage.setItem('currentUser', JSON.stringify({ username: username, token: token }));

        // return true to indicate successful login
        return true;
      } else {
        // return false to indicate failed login
        return false;
      }
    });*/
  }

  logout(): void {
    // clear token remove user from local storage to log user out
    this.token = null;
    localStorage.removeItem('authenticationToken');
  }

  getToken(): string {
    return this.token;
  }
}
