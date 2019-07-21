import {Component, OnInit} from '@angular/core';
import {ManagerEntitiesService} from '../service/manager-entities.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {switchMap} from 'rxjs/internal/operators/switchMap';
import {EntityFormProvider} from '../provider/entity-form-provider';
import {ResponseMessage} from '../../response/response-message';
import {AdminEntitiesService} from "../service/admin-entities.service";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-entity-edit',
  templateUrl: './entity-edit.component.html'
})
export class EntityEditComponent implements OnInit {

  successResponseMessage: ResponseMessage;
  errorResponseMessage: ResponseMessage;
  entityId: string;
  entitiesService: ManagerEntitiesService | AdminEntitiesService;

  showForm = false;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private entityFormProvider: EntityFormProvider,
              private authService: AuthService,
              private managerEntitiesService: ManagerEntitiesService,
              private adminEntitiesService: AdminEntitiesService) {
  }

  ngOnInit() {
    this.entityFormProvider.subscribeAllEditControls();
    this.entityFormProvider.entityForm.reset();

    this.initEntitiesService();

    this.activatedRoute.paramMap
      .pipe(
        switchMap((params: ParamMap) => {
          this.entityId = params.get('idValue');
          return this.entitiesService.read(this.entityId);
        })
      ).subscribe(
      value => {
        this.entityFormProvider.setControlValuesUsingEntityProperties(value.results[0]);
        this.showForm = true;
      }
    );
  }

  onSave() {
    this.clearSuccessResponseMessage();
    this.clearErrorResponseMessage();
    this.entitiesService.edit(this.entityId, this.entityFormProvider.getEntityEditDto())
      .subscribe(
        () => this.gotoList(),
        erm => this.errorResponseMessage = erm,
      );
  }

  onCancel() {
    this.gotoList();
  }

  gotoList() {
    this.router.navigate(['../../' + 'list'], {relativeTo: this.activatedRoute});
  }

  clearSuccessResponseMessage() {
    this.successResponseMessage = undefined;
  }

  clearErrorResponseMessage() {
    this.errorResponseMessage = undefined;
  }

  initEntitiesService() {
    this.entitiesService = this.authService.isManager
      ? this.managerEntitiesService
      : this.adminEntitiesService;
  }
}
