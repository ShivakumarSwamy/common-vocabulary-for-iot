import {Injectable} from '@angular/core';
import {UserCreateDto} from "../dto/user-create-dto";
import {HttpClient} from "@angular/common/http";
import {USERS_ENDPOINT} from "../api-endpoints";
import {catchError} from "rxjs/operators";
import {handleError} from "../handle-error";
import {UserDetails} from "../response/user-details";
import {Observable} from "rxjs";
import {ResultsSet} from "../response/results-set";

@Injectable()
export class UserService {

  constructor(private httpClient: HttpClient) {
  }

  create(userCreateDto: UserCreateDto) {
    return this.httpClient.post(USERS_ENDPOINT, userCreateDto)
      .pipe(
        catchError(handleError)
      );
  }

  read(): Observable<ResultsSet<UserDetails>> {
    return this.httpClient.get<ResultsSet<UserDetails>>(USERS_ENDPOINT)
      .pipe(
        catchError(handleError)
      )
  }
}