import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Observable } from 'rxjs';
import 'rxjs/add/operator/map'

import { AuthService } from './index';
import { User } from './index';

@Injectable()
export class UserService {
    
    private userUrl = 'http://localhost:8080/api/users';
    
    constructor(
        private http: Http,
        private authService: AuthService) {
    }

    getUsers(): Observable<User[]> {
        // add authorization header with jwt token
        let headers = new Headers({ 'Authorization': 'Bearer ' + this.authService.token });
        let options = new RequestOptions({ headers: headers });

        // get users from api
        return this.http.get(this.userUrl + '/get_users?page=1', options)
            .map((response: Response) => response.json());
    }
}