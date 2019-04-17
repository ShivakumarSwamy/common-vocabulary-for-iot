import {Component, Input} from '@angular/core';
import {UserDetails} from '../../response/user-details';

@Component({
  selector: 'app-profile-user-details',
  templateUrl: './profile-user-details.component.html'
})
export class ProfileUserDetailsComponent {

  @Input() userDetails: UserDetails;

}
