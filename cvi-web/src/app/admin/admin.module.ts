import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminWelcomeComponent } from './admin-welcome/admin-welcome.component';
import { AdminToolbarComponent } from './admin-toolbar/admin-toolbar.component';

@NgModule({
  declarations: [AdminHomeComponent, AdminWelcomeComponent, AdminToolbarComponent],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
