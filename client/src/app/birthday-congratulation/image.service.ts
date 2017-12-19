import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Image} from './image';
import {Observable} from 'rxjs/Observable';
import {environment} from '../../environments/environment';
import {catchError, tap} from 'rxjs/operators';
import {of} from 'rxjs/observable/of';

@Injectable()
export class ImageService {

  constructor(private http: HttpClient) {
  }


  getImgage(): Observable<string> {
    const url = `${environment.apiUrl}image/get`;
    return this.http.get<string>(url)
      .pipe(
        tap((image: string) => this.log(`fetched imagr ${JSON.stringify(image)}`)),
        catchError(this.handleError<string>(`get letter from back}`))
      );
  }

  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
    const url = `${environment.apiUrl}image/upload`;
    formdata.append('file', file);
    const req = new HttpRequest('POST', url, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);
  }


  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    console.log('ImageService: ' + message);
  }
}
