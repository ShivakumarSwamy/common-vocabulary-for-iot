import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';

import {Observable, throwError} from 'rxjs';
import {ResponseMessage} from '../response/response-message';
import {catchError} from 'rxjs/operators';
import {Router} from '@angular/router';
import {UserLoginDto} from "./dto/user-login-dto";
import {LOGIN_ENDPOINT} from "../api-endpoints";


function handleLoginError(error: HttpErrorResponse) {
  if (error.error instanceof ErrorEvent) {
    console.error('An error occurred:', error.error.message);
  } else {
    console.error(
      `Backend returned code ${error.status}, ` +
      `body was: ${error.error.message}`);

    if (error.status === 401) {
      return throwError({message: 'username or password is wrong'});
    } else if (error.status > 500) {
      return throwError({message: 'Server not available; please try again later.'});
    }
    return throwError(error.error);
  }
  return throwError({
    message: 'Something bad happened; please try again later.'
  });
}


@Injectable()
export class AuthService {
  isAuthenticated = false;
  isManager = false;
  isConsumer = false;
  isAdmin = false;

  username = '';
  redirectUrl = '/';
  token = '';
  role = '';

  constructor(private httpClient: HttpClient,
              private router: Router) {
  }


  login(userLoginDto: UserLoginDto): Observable<HttpResponse<ResponseMessage>> {
    return this.httpClient.post<ResponseMessage>(LOGIN_ENDPOINT, userLoginDto, {observe: 'response'})
      .pipe(
        catchError(handleLoginError),
      );
  }

  logout() {

    this.resetToDefaults();
    this.falsifyAllRolesAndAuth();
    this.router.navigate(['/login']);
  }

  resetToDefaults() {
    this.token = '';
    this.role = '';
    this.redirectUrl = '/';
  }

  falsifyAllRolesAndAuth() {
    this.isAuthenticated = false;
    this.isConsumer = false;
    this.isManager = false;
    this.isAdmin = false;
  }

  storeToken(httpHeaders: HttpHeaders) {
    this.token = httpHeaders.get('Authorization');

    if (this.token !== null) {
      this.parseToken();

      if (this.role) {
        this.isAuthenticated = true;
      }
    }
  }


  parseToken() {
    const parts = this.token.split('.');
    const decodedPayload = window.atob(parts[1]);
    const parsedPayload = JSON.parse(decodedPayload);
    this.role = parsedPayload.role;
    this.username = parsedPayload.username;

    if (this.role !== null) {
      if (this.role === 'Manager') {
        this.isManager = true;
      } else if (this.role === 'Consumer') {
        this.isConsumer = true;
      } else if (this.role === 'Admin') {
        this.isAdmin = true;
      }
    }
  }
}
