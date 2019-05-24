import {Component, Input, OnInit} from '@angular/core';
import {TopicProperties} from '../response/topic-properties';
import {AuthService} from '../../auth/auth.service';
import {ManagerEntitiesService} from '../service/manager-entities.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminEntitiesService} from "../service/admin-entities.service";

@Component({
  selector: 'app-topic-item-details',
  templateUrl: './topic-item-details.component.html'
})
export class TopicItemDetailsComponent implements OnInit {
  @Input() topicProperties: TopicProperties;

  deleteSuccess = false;

  topicsService: ManagerEntitiesService | AdminEntitiesService;

  constructor(private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private managerTopicsService: ManagerEntitiesService,
              private adminTopicsService: AdminEntitiesService) {
  }

  delete(uuid: string) {
    this.topicsService.delete(uuid)
      .subscribe(
        () => {
          this.deleteSuccess = true;
          this.gotoList();
        }
      );
  }

  gotoList() {
    this.router.navigate(['../' + 'list'], {relativeTo: this.activatedRoute});
  }

  ngOnInit(): void {
    this.initTopicsService();
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
