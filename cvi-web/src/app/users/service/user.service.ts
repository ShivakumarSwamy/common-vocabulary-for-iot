import {Injectable} from '@angular/core';
import {UserCreateDto} from "../dto/user-create-dto";
import {HttpClient} from "@angular/common/http";
import {USERS_ENDPOINT} from "../../api-endpoints";
import {catchError} from "rxjs/operators";
import {handleError} from "../../handle-error";

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
}
