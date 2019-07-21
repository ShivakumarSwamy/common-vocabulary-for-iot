import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../auth/auth.service";


@Component({
  selector: 'app-manager-toolbar',
  templateUrl: './manager-toolbar.component.html'
})
export class ManagerToolbarComponent implements OnInit {

  constructor(public authService: AuthService) {
  }

  ngOnInit() {
  }

}
