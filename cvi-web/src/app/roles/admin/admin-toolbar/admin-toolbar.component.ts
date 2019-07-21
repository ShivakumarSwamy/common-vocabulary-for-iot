import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../../auth/auth.service";

@Component({
  selector: 'app-admin-toolbar',
  templateUrl: './admin-toolbar.component.html'
})
export class AdminToolbarComponent implements OnInit {

  constructor(public authService: AuthService) {
  }

  ngOnInit() {
  }

}
