import {Component, Input, OnInit} from '@angular/core';
import {EntityProperties} from '../response/entity-properties';
import {AuthService} from '../../auth/auth.service';
import {ManagerEntitiesService} from '../service/manager-entities.service';
import {ActivatedRoute, Router} from '@angular/router';
import {AdminEntitiesService} from "../service/admin-entities.service";

@Component({
  selector: 'app-entity-item-details',
  templateUrl: './entity-item-details.component.html'
})
export class EntityItemDetailsComponent implements OnInit {

  @Input() entityProperties: EntityProperties;
  entitiesService: ManagerEntitiesService | AdminEntitiesService;

  deleteSuccess = false;

  constructor(private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private router: Router,
              private managerEntitiesService: ManagerEntitiesService,
              private adminEntitiesService: AdminEntitiesService) {
  }

  delete(uuid: string) {
    this.entitiesService.delete(uuid)
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
    this.initEntitiesService();
  }

  initEntitiesService() {
    if (this.authService.isManager) {
      this.entitiesService = this.managerEntitiesService;
    }

    if (this.authService.isAdmin) {
      this.entitiesService = this.adminEntitiesService;
    }
  }
}
