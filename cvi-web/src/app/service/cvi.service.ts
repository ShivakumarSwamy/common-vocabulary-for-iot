import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {COMPONENT_TYPES_ENDPOINT, MEANING_TERMS_ENDPOINT} from '../api-endpoints';
import {TermsMeaningResultsSet} from '../response/terms-meaning-results-set';
import {ResultsSet} from "../response/results-set";
import {ComponentTypeItemDetails} from "../response/component-type-item-details";
import {Observable} from "rxjs";
import {ResponseMessage} from "../response/response-message";
import {ComponentTypeDto} from "../dto/component-type-dto";
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


  createComponentType(componentTypeDto: ComponentTypeDto) {
    return this.httpClient.post<ResponseMessage>(COMPONENT_TYPES_ENDPOINT, componentTypeDto)
      .pipe(
        catchError(handleError)
      );
  }

  getAllComponentTypes(): Observable<ResultsSet<ComponentTypeItemDetails>> {
    return this.httpClient.get<ResultsSet<ComponentTypeItemDetails>>(COMPONENT_TYPES_ENDPOINT);
  }
}
