import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../auth/auth.service";

@Component({
  selector: 'app-entities-dashboard',
  templateUrl: './entities-dashboard.component.html'
})
export class EntitiesDashboardComponent implements OnInit {

  constructor(public authService: AuthService) {
  }

  ngOnInit() {
  }

}
