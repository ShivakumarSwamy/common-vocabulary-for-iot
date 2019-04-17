import {Component, OnInit} from '@angular/core';
import {ManagerTopicsService} from '../service/manager-topics.service';
import {TopicsResponse} from '../response/topics-response';

@Component({
  selector: 'app-topics-list',
  templateUrl: './topics-list.component.html'
})
export class TopicsListComponent implements OnInit {

  allTopics: TopicsResponse;
  showCreateTopic = false;

  constructor(private topicsService: ManagerTopicsService) {
  }

  ngOnInit() {
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

}
