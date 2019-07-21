import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MANAGER_ENTITIES_ENDPOINT} from '../../api-endpoints';
import {EntityCreateDto} from '../dto/entity-create-dto';
import {ResponseMessage} from '../../response/response-message';
import {EntitiesResponse} from '../response/entities-response';
import {catchError} from 'rxjs/operators';
import {handleError} from '../../handle-error';
import {EntityEditDto} from "../dto/entity-edit-dto";

@Injectable()
export class ManagerEntitiesService {

  constructor(private httpClient: HttpClient) {
  }

  create(entityCreateDto: EntityCreateDto) {
    return this.httpClient.post<ResponseMessage>(MANAGER_ENTITIES_ENDPOINT, entityCreateDto)
      .pipe(
        catchError(handleError)
      );
  }

  read(id: string) {
    return this.httpClient.get<EntitiesResponse>(MANAGER_ENTITIES_ENDPOINT + '/' + id);
  }

  readAll() {
    return this.httpClient.get<EntitiesResponse>(MANAGER_ENTITIES_ENDPOINT);
  }

  search(termsText: string) {
    return this.httpClient.post<EntitiesResponse>(MANAGER_ENTITIES_ENDPOINT + '/search',
      null,
      {params: {search_query: termsText}}
    );
  }

  edit(id: string, entityEditDto: EntityEditDto) {
    return this.httpClient.put(MANAGER_ENTITIES_ENDPOINT + '/' + id, entityEditDto);
  }

  delete(id: string) {
    return this.httpClient.delete(MANAGER_ENTITIES_ENDPOINT + '/' + id);
  }
}
