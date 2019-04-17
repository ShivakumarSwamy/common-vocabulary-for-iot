import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HARDWARE_TYPES_ENDPOINT, MEANING_TERMS_ENDPOINT} from '../api-endpoints';
import {TermsMeaningResultsSet} from '../response/terms-meaning-results-set';
import {ResultsSet} from "../response/results-set";
import {HardwareTypeItemDetails} from "../response/hardware-type-item-details";
import {Observable} from "rxjs";
import {ResponseMessage} from "../response/response-message";
import {HardwareTypeDto} from "../dto/hardware-type-dto";
import {handleError} from "../handle-error";
import {catchError} from "rxjs/operators";

@Injectable()
export class CviService {

  constructor(private httpClient: HttpClient) {
  }

  getMeaningTermsSearch(termsText: string): Observable<TermsMeaningResultsSet> {
    return this.httpClient.post<TermsMeaningResultsSet>(
      MEANING_TERMS_ENDPOINT,
      null,
      {params: {search_query: termsText}}
    );
  }


  createHardwareType(hardwareTypeDto: HardwareTypeDto) {
    return this.httpClient.post<ResponseMessage>(HARDWARE_TYPES_ENDPOINT, hardwareTypeDto)
      .pipe(
        catchError(handleError)
      );
  }

  getAllHardwareTypes(): Observable<ResultsSet<HardwareTypeItemDetails>> {
    return this.httpClient.get<ResultsSet<HardwareTypeItemDetails>>(HARDWARE_TYPES_ENDPOINT);
  }
}
