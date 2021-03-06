import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UsersRoutingModule} from './users-routing.module';
import {UserCreateComponent} from './user-create/user-create.component';
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [UserCreateComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    UsersRoutingModule
  ]
})
export class UsersModule {
}
