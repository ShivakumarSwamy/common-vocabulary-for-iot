import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AdminRoutingModule} from './admin-routing.module';
import {AdminHomeComponent} from './admin-home/admin-home.component';
import {AdminWelcomeComponent} from './admin-welcome/admin-welcome.component';
import {AdminToolbarComponent} from './admin-toolbar/admin-toolbar.component';
import {AdminUsersListComponent} from './admin-users-list/admin-users-list.component';
import {AdminItemUserDetailsComponent} from './admin-item-user-details/admin-item-user-details.component';

@NgModule({
  declarations: [AdminHomeComponent, AdminWelcomeComponent, AdminToolbarComponent, AdminUsersListComponent, AdminItemUserDetailsComponent],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule {
}
