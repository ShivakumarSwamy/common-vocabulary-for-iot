import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MEANING_TERMS_ENDPOINT} from '../api-endpoints';
import {TermsMeaningResultsSet} from '../response/terms-meaning-results-set';

@Injectable()
export class CviService {

  constructor(private httpClient: HttpClient) {
  }

  getMeaningTermsSearch(termsText: string) {
    return this.httpClient.post<TermsMeaningResultsSet>(
      MEANING_TERMS_ENDPOINT,
      null,
      {params: {search_query: termsText}}
    );
  }
  //
  // createHardwareType(chtdto: HardwareTypeDto) {
  //   return this.httpClient.post<ResponseMessage>(
  //     CUSTOM_HARDWARE_TYPES_ENDPOINT,
  //     chtdto
  //   ).pipe(
  //     catchError(handleError)
  //   );
  // }
  //
  // getAllHardwareTypes() {
  //   return this.httpClient.get<HardwareTypesResponse>(CUSTOM_HARDWARE_TYPES_ENDPOINT);
  // }
}
