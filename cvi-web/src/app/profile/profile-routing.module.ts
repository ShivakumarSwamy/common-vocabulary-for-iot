import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProfileHomeComponent} from './profile-home/profile-home.component';
import {AuthGuard} from "../auth/auth.guard";

const profileRoutes: Routes = [
  {
    path: '',
    component: ProfileHomeComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(profileRoutes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule {
}
