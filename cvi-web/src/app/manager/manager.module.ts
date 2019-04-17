import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ManagerRoutingModule} from './manager-routing.module';
import {ManagerToolbarComponent} from './manager-toolbar/manager-toolbar.component';
import {ManagerWelcomeComponent} from './manager-welcome/manager-welcome.component';
import {ManagerHomeComponent} from './manager-home/manager-home.component';

@NgModule({
  declarations: [
    ManagerToolbarComponent,
    ManagerWelcomeComponent,
    ManagerHomeComponent
  ],
  imports: [
    CommonModule,
    ManagerRoutingModule
  ]
})
export class ManagerModule {
}
