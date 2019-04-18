import {Injectable} from "@angular/core";
import {ADMIN_TOPICS_ENDPOINT, MANAGER_TOPICS_ENDPOINT} from "../../api-endpoints";
import {HttpClient} from "@angular/common/http";
import {TopicsResponse} from "../response/topics-response";
import {TopicEditDto} from "../dto/topic-edit-dto";


@Injectable()
export class AdminTopicsService {


  constructor(private httpClient: HttpClient) {
  }

  read(id: string) {
    return this.httpClient.get<TopicsResponse>(ADMIN_TOPICS_ENDPOINT + '/' + id);
  }

  readAll() {
    return this.httpClient.get<TopicsResponse>(ADMIN_TOPICS_ENDPOINT);
  }

  edit(id: string, topicEditDto: TopicEditDto) {
    return this.httpClient.put(ADMIN_TOPICS_ENDPOINT + '/' + id, topicEditDto);
  }

  delete(id: string) {
    return this.httpClient.delete(ADMIN_TOPICS_ENDPOINT + '/' + id);
  }
}
