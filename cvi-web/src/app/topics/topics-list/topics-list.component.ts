import {Component, OnInit} from '@angular/core';
import {ManagerTopicsService} from '../service/manager-topics.service';
import {TopicsResponse} from '../response/topics-response';
import {AuthService} from "../../auth/auth.service";
import {AdminTopicsService} from "../service/admin-topics.service";

@Component({
  selector: 'app-topics-list',
  templateUrl: './topics-list.component.html'
})
export class TopicsListComponent implements OnInit {

  allTopics: TopicsResponse;
  showCreateTopic = false;

  topicsService: ManagerTopicsService | AdminTopicsService;

  constructor(private authService: AuthService,
              private managerTopicsService: ManagerTopicsService,
              private adminTopicsService: AdminTopicsService) {
  }

  ngOnInit() {
    this.initTopicsService();
    this.topicsService.readAll()
      .subscribe(
        value => {
          this.allTopics = value;
          this.showOrHideCreateTopic();
        }
      );
  }

  showOrHideCreateTopic() {
    this.showCreateTopic = !this.validResults();
  }


  validResults() {
    return this.allTopics && this.allTopics.results && this.allTopics.results.length;
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
