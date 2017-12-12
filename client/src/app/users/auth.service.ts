import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { catchError, map, tap } from 'rxjs/operators';
import { of } from 'rxjs/observable/of';
import { User } from './user';

@Injectable()
export class AuthService {

  public static token: string;
  public static currentUser: User;

  private authUrl = 'http://localhost:8080/auth';

  public static logout(): void {
    // clear token remove user from local storage to log user out
    AuthService.token = null;
    localStorage.removeItem('token');
    localStorage.removeItem('currentUser');
  }

  public static getToken(): string {
    return AuthService.token;
  }

  constructor(private http: HttpClient) {
    // set token if saved in local storage
    if (localStorage.getItem('token') && localStorage.getItem('currentUser')) {
      AuthService.token = JSON.parse(localStorage.getItem('token'));
      AuthService.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }
  }

  login (email: string, password: string): Observable<string> {
    return this.http
      .post<string>(this.authUrl, {email: email, password: password})
        .pipe(
          tap(data => {
            if (data['token']) {
              this.log(`fetched token: ${data['token']}`);
              this.log(`fetched user: ${JSON.stringify(data['user'])}`);
              AuthService.token = data['token'];
              this.log(JSON.stringify({ email: email, token:  AuthService.token}));
              localStorage.setItem('token',  AuthService.token);
              localStorage.setItem('currentUser', JSON.stringify(data['user']));
              return AuthService.token;
            }
          }),
          catchError(this.handleError('login', ''))
        );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      this.logError(error); // log to console instead
      this.log(`${operation} failed: ${error.message}`);
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log('AuthService: ' + message);
  }

  private logError(message: string) {
    console.error('AuthService: ' + message);
  }
}
