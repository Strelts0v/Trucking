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

  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) {
  }

  getUsers(page: number, size: number): Observable<User[]> {
    if (page <= 0 || size <= 0) {
      return;
    }
    // get users using api
    const url = `${environment.apiUrl}${this.userUrl}/${this.getUsersUrl}?page=${page}&size=${size}`;
    this.log(url);
    return this.http.get<User[]>(url)
      .pipe(
        tap(data => this.log(`fetched users: ${JSON.stringify(data)}`)),
        catchError(this.handleError<User[]>(`getUsers`))
      );
  }

  addUser(user: User): Observable<User> {
    // add user using api
    const url = `${environment.apiUrl}${this.userUrl}/${this.addUserUrl}`;
    this.log(url);
    return this.http.post<User>(url, user, this.httpOptions)
      .pipe(
        tap(data => this.log(`added user: ${data}`)),
        catchError(this.handleError<User>(`addUser`, new User()))
      );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  size(): Observable<number> {
    const url = `${environment.apiUrl}${this.userUrl}/${this.userCountUrl}`;
    return this.http.get<number>(url)
      .pipe(
        tap(data => `fetched user count: ${data}`),
        catchError(this.handleError<number>(`size`, 0))
      );
  }

  private log(message: string) {
    console.log('UserService: ' + message);
  }
}
