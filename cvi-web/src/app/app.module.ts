import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {UsersModule} from "./users/users.module";
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {UserService} from "./service/user.service";
import {AuthModule} from "./auth/auth.module";
import {ConsumerModule} from "./consumer/consumer.module";
import {ManagerModule} from "./manager/manager.module";
import {AdminModule} from "./admin/admin.module";
import {httpInterceptorProviders} from "./interceptors/index-interceptors";
import {CviService} from "./service/cvi.service";

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AuthModule,
    UsersModule,
    ConsumerModule,
    ManagerModule,
    AdminModule,
    AppRoutingModule
  ],
  providers: [
    UserService,
    CviService,
    httpInterceptorProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
