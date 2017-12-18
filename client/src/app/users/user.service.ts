import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, tap } from 'rxjs/operators';

import { AuthService } from './auth.service';
import { User } from './user';
import { environment } from '../../environments/environment';


@Injectable()
export class UserService {

  private userUrl = 'users';
  private getUsersUrl = 'get_users';
  private addUserUrl = 'add_user';
  private userCountUrl = 'count';
  private updateUserUrl = 'update_user';
  private deleteUserUrl = 'delete_user';

  private apiUrl: string;

  constructor(private http: HttpClient) {
    this.apiUrl = `${environment.apiUrl}${this.userUrl}/`;
  }

  getUsers(page: number, size: number): Observable<User[]> {
    const url = `${this.apiUrl}${this.getUsersUrl}/${page}/${size}`;
    this.log(url);
    return this.http.get<User[]>(url)
      .pipe(
        tap(data => this.log(`fetched users: ${JSON.stringify(data)}`)),
        catchError(this.handleError<User[]>(`getUsers`))
      );
  }

  addUser(user: User): Observable<User> {
    const url = `${this.apiUrl}${this.addUserUrl}`;
    this.log(url);
    user = this.fixDateFormat(user);
    this.log(`User after updating date: ${JSON.stringify(user)}`);
    return this.http.post<User>(url, user)
      .pipe(
        tap(data => this.log(`added user: ${data}`)),
        catchError(this.handleError<User>(`addUser`, new User()))
      );
  }

  updateUser(user: User): Observable<User> {
    const url = `${this.apiUrl}${this.updateUserUrl}`;
    this.log(url);
    return this.http.put<User>(url, user)
      .pipe(
        tap((user: User) => this.log(`user was successfully updated: ${JSON.stringify(user)}`)),
        catchError(this.handleError<User>(`updateUser`, new User()))
      );
  }

  deleteUser(user: User): Observable<User> {
    const url = `${this.apiUrl}${this.deleteUserUrl}`;
    this.log(url);
    return this.http.post<User>(url, user)
      .pipe(
        tap((user: User) => this.log(`user was successfully deleted: ${JSON.stringify(user)}`)),
        catchError(this.handleError<User>(`deleteUser`, new User()))
      );
  }

  getUserCount(): Observable<number> {
    const url = `${this.apiUrl}${this.userCountUrl}`;
    return this.http.get<number>(url)
      .pipe(
        tap(data => `fetched user count: ${data}`),
        catchError(this.handleError<number>(`getUserCount`, 0))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log('UserService: ' + message);
  }

  private fixDateFormat(user: User): User {
    let date = user.birthday;
    date = date.substr(0, 10);
    user.birthday = date;
    return user;
  }
}
