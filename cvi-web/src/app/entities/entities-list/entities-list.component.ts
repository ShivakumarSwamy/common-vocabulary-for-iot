import {Component, OnDestroy, OnInit} from '@angular/core';
import {ManagerEntitiesService} from '../service/manager-entities.service';
import {EntitiesResponse} from '../response/entities-response';
import {AuthService} from "../../auth/auth.service";
import {AdminEntitiesService} from "../service/admin-entities.service";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-entities-list',
  templateUrl: './entities-list.component.html'
})
export class EntitiesListComponent implements OnInit, OnDestroy {

  allEntities$: Subscription;

  allEntities: EntitiesResponse;
  entitiesService: ManagerEntitiesService | AdminEntitiesService;

  showCreateEntity = false;

  constructor(private authService: AuthService,
              private managerEntitiesService: ManagerEntitiesService,
              private adminEntitiesService: AdminEntitiesService) {
  }

  ngOnInit() {
    this.initEntitiesService();

    this.allEntities$ = this.entitiesService.readAll()
      .subscribe(
        value => {
          this.allEntities = value;
          this.showOrHideCreateEntity();
        }
      );

  }

  showOrHideCreateEntity() {
    this.showCreateEntity = !this.validResults();
  }


  validResults() {
    return this.allEntities && this.allEntities.results && this.allEntities.results.length;
  }

  initEntitiesService() {
    this.entitiesService = this.authService.isAdmin
      ? this.adminEntitiesService
      : this.managerEntitiesService;
  }

  ngOnDestroy(): void {
    if (this.allEntities$) this.allEntities$.unsubscribe();
  }


}
