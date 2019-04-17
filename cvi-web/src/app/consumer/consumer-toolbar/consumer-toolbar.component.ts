import {Component} from '@angular/core';
import {AuthService} from '../../auth/auth.service';

@Component({
  selector: 'app-consumer-toolbar',
  templateUrl: './consumer-toolbar.component.html'
})
export class ConsumerToolbarComponent {

  constructor(public authService: AuthService) {
  }

}
