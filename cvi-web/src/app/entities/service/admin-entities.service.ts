import {Injectable} from "@angular/core";
import {ADMIN_ENTITIES_ENDPOINT} from "../../api-endpoints";
import {HttpClient} from "@angular/common/http";
import {EntitiesResponse} from "../response/entities-response";
import {EntityEditDto} from "../dto/entity-edit-dto";


@Injectable()
export class AdminEntitiesService {


  constructor(private httpClient: HttpClient) {
  }

  read(id: string) {
    return this.httpClient.get<EntitiesResponse>(ADMIN_ENTITIES_ENDPOINT + '/' + id);
  }

  readAll() {
    return this.httpClient.get<EntitiesResponse>(ADMIN_ENTITIES_ENDPOINT);
  }

  search(termsText: string) {
    return this.httpClient.post<EntitiesResponse>(ADMIN_ENTITIES_ENDPOINT + '/search',
      null,
      {params: {search_query: termsText}}
    );
  }

  edit(id: string, entityEditDto: EntityEditDto) {
    return this.httpClient.put(ADMIN_ENTITIES_ENDPOINT + '/' + id, entityEditDto);
  }

  delete(id: string) {
    return this.httpClient.delete(ADMIN_ENTITIES_ENDPOINT + '/' + id);
  }
}
