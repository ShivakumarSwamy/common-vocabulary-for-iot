import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-topics-home',
  templateUrl: './topics-home.component.html'
})
export class TopicsHomeComponent implements OnInit {

  constructor(public authService: AuthService) {
  }

  ngOnInit() {
  }

}
