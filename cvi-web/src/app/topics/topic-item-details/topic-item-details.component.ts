import {Component, Input} from '@angular/core';
import {TopicProperties} from '../response/topic-properties';
import {AuthService} from '../../auth/auth.service';
import {ManagerTopicsService} from '../service/manager-topics.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-topic-item-details',
  templateUrl: './topic-item-details.component.html'
})
export class TopicItemDetailsComponent {
  @Input() topicProperties: TopicProperties;

  deleteSuccess = false;

  constructor(private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private managerTopicsService: ManagerTopicsService) {
  }

  delete(uuid: string) {
    this.managerTopicsService.delete(uuid)
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
}
