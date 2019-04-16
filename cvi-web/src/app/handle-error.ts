import { HttpErrorResponse } from '@angular/common/http';
import { throwError } from 'rxjs';

export function handleError(error: HttpErrorResponse) {
  if (error.error instanceof ErrorEvent) {
    console.error('An error occurred:', error.error.message);
  } else {
    console.error(
      `Backend returned code ${ error.status }, ` +
      `body was: ${ error.error.message }`);
    if (error.status > 500) {
      return throwError({ message: 'Server not available, please try again later.' });
    }
    return throwError(error.error);
  }
  return throwError({
    message: 'Something bad happened, please try again later.'
  });
}
