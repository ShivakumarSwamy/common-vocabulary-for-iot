import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CONSUMER_ENTITIES_ENDPOINT} from '../../api-endpoints';
import {EntitiesResponse} from '../response/entities-response';

@Injectable()
export class ConsumerEntitiesService {

  constructor(private httpClient: HttpClient) {
  }

  search(termsText: string) {
    return this.httpClient.post<EntitiesResponse>(CONSUMER_ENTITIES_ENDPOINT + '/search',
      null,
      {params: {search_query: termsText}}
    );
  }

}
