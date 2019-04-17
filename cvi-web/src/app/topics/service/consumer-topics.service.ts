import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CONSUMER_TOPICS_ENDPOINT} from '../../api-endpoints';
import {TopicsResponse} from '../response/topics-response';

@Injectable()
export class ConsumerTopicsService {

  constructor(private httpClient: HttpClient) {
  }

  search(termsText: string) {
    return this.httpClient.post<TopicsResponse>(CONSUMER_TOPICS_ENDPOINT,
      null,
      {params: {search_query: termsText}}
    );
  }

}
