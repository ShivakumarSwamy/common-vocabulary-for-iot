import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MANAGER_TOPICS_ENDPOINT} from '../../api-endpoints';
import {TopicDTO} from '../dto/topic-dto';
import {ResponseMessage} from '../../response/response-message';
import {TopicsResponse} from '../response/topics-response';
import {catchError} from 'rxjs/operators';
import {handleError} from '../../handle-error';

@Injectable()
export class ManagerTopicsService {

  constructor(private httpClient: HttpClient) {
  }

  read(id: string) {
    return this.httpClient.get<TopicsResponse>(MANAGER_TOPICS_ENDPOINT + '/' + id);
  }

  readAll() {
    return this.httpClient.get<TopicsResponse>(MANAGER_TOPICS_ENDPOINT);
  }

  search(termsText: string) {
    return this.httpClient.post<TopicsResponse>(MANAGER_TOPICS_ENDPOINT,
      null,
      {params: {search_query: termsText}}
    );
  }

  createTopic(topicDTO: TopicDTO) {
    return this.httpClient.post<ResponseMessage>(MANAGER_TOPICS_ENDPOINT, topicDTO)
      .pipe(
        catchError(handleError)
      );
  }

  edit(id: string, topicDTO: TopicDTO) {
    return this.httpClient.put(MANAGER_TOPICS_ENDPOINT + '/' + id, topicDTO);
  }

  delete(id: string) {
    return this.httpClient.delete(MANAGER_TOPICS_ENDPOINT + '/' + id);
  }
}
