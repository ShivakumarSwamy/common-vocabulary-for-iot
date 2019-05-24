import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ManagerEntitiesService} from '../service/manager-entities.service';
import {EntitiesResponse} from '../response/entities-response';
import {ConsumerEntitiesService} from '../service/consumer-entities.service';
import {AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-topics-search',
  templateUrl: './topics-search.component.html'
})
export class TopicsSearchComponent implements OnInit {
  searchControl = this.formBuilder.control('', Validators.required);
  termsText = '';
  showSorryMessage = false;

  topicsResponse: EntitiesResponse;

  topicsService: ManagerEntitiesService | ConsumerEntitiesService;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private managerTopicsService: ManagerEntitiesService,
              private consumerTopicsService: ConsumerEntitiesService) {
  }

  ngOnInit() {
    this.searchControl.valueChanges
      .subscribe(
        (value: string) => this.termsText = value
      );
    this.initTopicsService();
  }

  getResults() {
    this.clearShowSorryMessage();

    if (this.validTermsText()) {
      this.topicsService.search(this.replaceTermsTextWhitespacesWithPlusSign())
        .subscribe(
          value => {
            this.topicsResponse = value;
            this.showSorryMessageOrNot();
          }
        );
    }
  }

  clearShowSorryMessage() {
    this.showSorryMessage = false;
  }

  validResults() {
    return this.topicsResponse && this.topicsResponse.results && this.topicsResponse.results.length;
  }

  showSorryMessageOrNot() {
    this.showSorryMessage = !this.validResults();
  }

  validTermsText() {
    return this.termsText && this.termsText !== '';
  }

  replaceTermsTextWhitespacesWithPlusSign() {
    return this.termsText.replace(/\s+/g, '+').toLowerCase();
  }

  initTopicsService() {
    if (this.authService.isManager) {
      this.topicsService = this.managerTopicsService;
    }

    if (this.authService.isConsumer || this.authService.isAdmin) {
      this.topicsService = this.consumerTopicsService;
    }

    // default
    if (!this.topicsService) {
      this.topicsService = this.managerTopicsService;
    }
  }
}
