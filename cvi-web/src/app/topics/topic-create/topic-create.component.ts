import {Component, OnInit} from '@angular/core';
import {ManagerTopicsService} from '../service/manager-topics.service';
import {ResponseMessage} from '../../response/response-message';
import {TopicFormProvider} from '../provider/topic-form-provider';

@Component({
  selector: 'app-topic-create',
  templateUrl: './topic-create.component.html'
})
export class TopicCreateComponent implements OnInit {

  successResponseMessage: ResponseMessage;
  errorResponseMessage: ResponseMessage;

  constructor(private topicFormProvider: TopicFormProvider,
              private topicsService: ManagerTopicsService) {
  }

  ngOnInit() {

    this.topicFormProvider.subscribeAllCreateControls();
    this.topicFormProvider.topicsForm.reset();
    this.topicFormProvider.initializeWithDefaultValuesForControls();
  }


  onSubmit() {
    this.clearSuccessResponseMessage();
    this.clearErrorResponseMessage();
    this.topicsService.createTopic(this.topicFormProvider.getTopicCreateDto())
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
