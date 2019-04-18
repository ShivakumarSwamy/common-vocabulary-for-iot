import {Component, Input, OnInit} from '@angular/core';
import {TopicProperties} from '../response/topic-properties';
import {AuthService} from '../../auth/auth.service';
import {ManagerTopicsService} from '../service/manager-topics.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminTopicsService} from "../service/admin-topics.service";

@Component({
  selector: 'app-topic-item-details',
  templateUrl: './topic-item-details.component.html'
})
export class TopicItemDetailsComponent implements OnInit {
  @Input() topicProperties: TopicProperties;

  deleteSuccess = false;

  topicsService: ManagerTopicsService | AdminTopicsService;

  constructor(private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private managerTopicsService: ManagerTopicsService,
              private adminTopicsService: AdminTopicsService) {
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
