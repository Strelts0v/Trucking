import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, tap } from 'rxjs/operators';

import { AuthService } from './auth.service';
import { User } from './user';


@Injectable()
export class UserService {

  private userUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) {
  }

  getUsers(page: number): Observable<User[]> {
    if (page <= 0) {
      return;
    }

    const httpOptions = {
      headers: new HttpHeaders({
        // add authorization header with jwt token
        'Authorization': `Bearer ${AuthService.token}`
      })
    };

    // get users from api
    return this.http.get<User[]>(this.userUrl + `/get_users?page=${page}`, httpOptions)
      .pipe(
        tap(users => this.log(`fetched users`)),
        catchError(this.handleError('getUsers', []))
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
}
