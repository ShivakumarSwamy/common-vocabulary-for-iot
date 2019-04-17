import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ProfileRoutingModule} from './profile-routing.module';
import {ProfileHomeComponent} from './profile-home/profile-home.component';
import {ProfileUserDetailsComponent} from './profile-user-details/profile-user-details.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    ProfileHomeComponent,
    ProfileUserDetailsComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ProfileRoutingModule
  ]
})
export class ProfileModule {
}
