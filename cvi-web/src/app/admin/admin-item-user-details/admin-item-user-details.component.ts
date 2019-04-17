import {Component, Input} from '@angular/core';
import {UserDetails} from "../../response/user-details";

@Component({
  selector: 'app-admin-item-user-details',
  templateUrl: './admin-item-user-details.component.html'
})
export class AdminItemUserDetailsComponent {

  @Input() userDetails: UserDetails;
}
