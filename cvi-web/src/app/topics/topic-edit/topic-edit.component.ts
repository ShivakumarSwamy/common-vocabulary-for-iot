import {Component, OnInit} from '@angular/core';
import {ManagerTopicsService} from '../service/manager-topics.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {switchMap} from 'rxjs/internal/operators/switchMap';
import {TopicFormProvider} from '../provider/topic-form-provider';
import {ResponseMessage} from '../../response/response-message';
import {AdminTopicsService} from "../service/admin-topics.service";
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-topic-edit',
  templateUrl: './topic-edit.component.html'
})
export class TopicEditComponent implements OnInit {

  successResponseMessage: ResponseMessage;
  errorResponseMessage: ResponseMessage;
  topicId: string;

  topicsService: ManagerTopicsService | AdminTopicsService;

  showForm = false;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router,
              private topicFormProvider: TopicFormProvider,
              private authService: AuthService,
              private managerTopicsService: ManagerTopicsService,
              private adminTopicsService: AdminTopicsService) {
  }

  ngOnInit() {
    this.topicFormProvider.subscribeAllEditControls();
    this.topicFormProvider.topicsForm.reset();

    this.initTopicsService();

    this.activatedRoute.paramMap
      .pipe(
        switchMap((params: ParamMap) => {
          this.topicId = params.get('idValue');
          return this.topicsService.read(this.topicId);
        })
      ).subscribe(
      value => {
        this.topicFormProvider.setControlValuesUsingTopicProperties(value.results[0]);
        this.showForm = true;
      }
    );
  }

  onSave() {
    this.clearSuccessResponseMessage();
    this.clearErrorResponseMessage();
    this.topicsService.edit(this.topicId, this.topicFormProvider.getTopicEditDto())
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

  initTopicsService() {
    if (this.authService.isManager) {
      this.topicsService = this.managerTopicsService;
    }

    if (this.authService.isAdmin) {
      this.topicsService = this.adminTopicsService;
    }

    // default
    if (!this.topicsService) {
      this.topicsService = this.managerTopicsService;
    }
  }
}
