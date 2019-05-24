import {Component, OnInit} from '@angular/core';
import {ManagerEntitiesService} from '../service/manager-entities.service';
import {EntitiesResponse} from '../response/entities-response';
import {AuthService} from "../../auth/auth.service";
import {AdminTopicsService} from "../service/admin-entities.service";

@Component({
  selector: 'app-topics-list',
  templateUrl: './topics-list.component.html'
})
export class TopicsListComponent implements OnInit {

  allTopics: EntitiesResponse;
  showCreateTopic = false;

  topicsService: ManagerEntitiesService | AdminTopicsService;

  constructor(private authService: AuthService,
              private managerTopicsService: ManagerEntitiesService,
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
