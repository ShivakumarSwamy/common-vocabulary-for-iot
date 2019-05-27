import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {ManagerEntitiesService} from '../service/manager-entities.service';
import {EntitiesResponse} from '../response/entities-response';
import {ConsumerEntitiesService} from '../service/consumer-entities.service';
import {AuthService} from '../../auth/auth.service';
import {Subscription} from "rxjs";
import {AdminEntitiesService} from "../service/admin-entities.service";

@Component({
  selector: 'app-entities-search',
  templateUrl: './entities-search.component.html'
})
export class EntitiesSearchComponent implements OnInit, OnDestroy {

  private termsText$: Subscription;

  entitiesResponse: EntitiesResponse;
  entitiesService: ManagerEntitiesService | ConsumerEntitiesService | AdminEntitiesService;

  searchControl = this.formBuilder.control('', Validators.required);
  termsText = '';
  showSorryMessage = false;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private adminEntitiesService: AdminEntitiesService,
              private managerEntitiesService: ManagerEntitiesService,
              private consumerEntitiesService: ConsumerEntitiesService) {
  }

  ngOnInit() {
    this.termsText$ = this.searchControl.valueChanges
      .subscribe(
        (value: string) => this.termsText = value
      );
    this.initEntitiesService();
  }

  initEntitiesService() {

    if (this.authService.isAdmin) {
      this.entitiesService = this.adminEntitiesService;
    }

    if (this.authService.isManager) {
      this.entitiesService = this.managerEntitiesService;
    }

    if (this.authService.isConsumer) {
      this.entitiesService = this.consumerEntitiesService;
    }
  }

  getResults() {
    this.clearShowSorryMessage();

    if (this.validTermsText()) {
      this.entitiesService.search(this.replaceTermsTextWhitespacesWithPlusSign())
        .subscribe(
          value => {
            this.entitiesResponse = value;
            this.showSorryMessageOrNot();
          }
        );
    }
  }

  clearShowSorryMessage() {
    this.showSorryMessage = false;
  }

  validResults() {
    return this.entitiesResponse && this.entitiesResponse.results && this.entitiesResponse.results.length;
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

  ngOnDestroy(): void {
    this.termsText$.unsubscribe();
  }
}
