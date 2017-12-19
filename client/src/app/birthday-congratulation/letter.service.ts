import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {of} from 'rxjs/observable/of';
import {Observable} from 'rxjs/Observable';
import {Letter} from './letter';
import {environment} from '../../environments/environment';
import {catchError, tap} from 'rxjs/operators';

@Injectable()
export class LetterService {

  constructor(private http: HttpClient) { }


  updateLetter(letter: Letter) {
    const url = `${environment.apiUrl}letter/update`;
    console.log(url);
    this.http.post<Letter>(url,letter)
      .pipe(
      tap((_ => this.log(`updated letter`)),
      catchError(this.handleError<Letter>('updateLetter'))
    ));
  }


  getLetter(): Observable<Letter>{
    const url = `${environment.apiUrl}letter/read`;
    return this.http.get<Letter>(url)
      .pipe(
        tap((letter: Letter) => this.log(`fetched letter ${JSON.stringify(letter)}`)),
        catchError(this.handleError<Letter>(`get letter from back}`))
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
    console.log('LetterService: ' + message);
  }

}
