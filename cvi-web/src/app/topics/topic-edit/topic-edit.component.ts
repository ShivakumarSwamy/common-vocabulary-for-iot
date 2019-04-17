import {Component, OnInit} from '@angular/core';
import {ManagerTopicsService} from '../service/manager-topics.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {switchMap} from 'rxjs/internal/operators/switchMap';
import {TopicFormProvider} from '../provider/topic-form-provider';
import {ResponseMessage} from '../../response/response-message';

@Component({
  selector: 'app-topic-edit',
  templateUrl: './topic-edit.component.html'
})
export class TopicEditComponent implements OnInit {

  successResponseMessage: ResponseMessage;
  errorResponseMessage: ResponseMessage;
  topicId: string;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private topicFormProvider: TopicFormProvider,
              private managerTopicsService: ManagerTopicsService) {
  }

  ngOnInit() {
    this.topicFormProvider.subscribeAllControls();
    this.topicFormProvider.topicsForm.reset();
    this.activatedRoute.paramMap
      .pipe(
        switchMap((params: ParamMap) => {
          this.topicId = params.get('id');
          return this.managerTopicsService.read(this.topicId);
        })
      ).subscribe(
      value => this.topicFormProvider.setControlValuesUsingTopicProperties(value.results[0])
    );
  }

  onSave() {
    this.clearSuccessResponseMessage();
    this.clearErrorResponseMessage();
    this.managerTopicsService.edit(this.topicId, this.topicFormProvider.getTopicDTO())
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
}
