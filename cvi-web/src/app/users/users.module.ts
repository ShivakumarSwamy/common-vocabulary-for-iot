import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {UsersRoutingModule} from './users-routing.module';
import {UserCreateComponent} from './user-create/user-create.component';
import {ReactiveFormsModule} from "@angular/forms";
import {UserService} from "./service/user.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [UserCreateComponent],
  imports: [
    CommonModule,
    HttpClientModule,
    ReactiveFormsModule,
    UsersRoutingModule
  ],
  providers: [
    UserService
  ]
})
export class UsersModule {
}
