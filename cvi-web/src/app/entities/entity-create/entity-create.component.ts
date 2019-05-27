import {Component, OnInit} from '@angular/core';
import {ManagerEntitiesService} from '../service/manager-entities.service';
import {ResponseMessage} from '../../response/response-message';
import {EntityFormProvider} from '../provider/entity-form-provider';

@Component({
  selector: 'app-topic-create',
  templateUrl: './entity-create.component.html'
})
export class EntityCreateComponent implements OnInit {

  successResponseMessage: ResponseMessage;
  errorResponseMessage: ResponseMessage;

  constructor(private entityFormProvider: EntityFormProvider,
              private managerEntitiesService: ManagerEntitiesService) {
  }

  ngOnInit() {

    this.entityFormProvider.subscribeAllCreateControls();
    this.entityFormProvider.entityForm.reset();
    this.entityFormProvider.initializeWithDefaultValuesForControls();
  }


  onSubmit() {
    this.clearSuccessResponseMessage();
    this.clearErrorResponseMessage();
    this.managerEntitiesService.create(this.entityFormProvider.getEntityCreateDto())
      .subscribe(
        srm => this.successResponseMessage = srm,
        erm => this.errorResponseMessage = erm,
      );
  }

  clearSuccessResponseMessage() {
    this.successResponseMessage = undefined;
  }

  clearErrorResponseMessage() {
    this.errorResponseMessage = undefined;
  }
}
