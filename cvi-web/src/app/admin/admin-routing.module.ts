import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AuthGuard} from "../auth/auth.guard";
import {AdminHomeComponent} from "./admin-home/admin-home.component";
import {AdminWelcomeComponent} from "./admin-welcome/admin-welcome.component";
import {AdminUsersListComponent} from "./admin-users-list/admin-users-list.component";

const routes: Routes = [
  {
    path: 'admin',
    component: AdminHomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        canActivateChild: [AuthGuard],
        children: [
          {
            path: '',
            component: AdminWelcomeComponent
          },
          {
            path: 'users',
            component: AdminUsersListComponent
          }
          // ,
          // {
          //   path: 'topics',
          //   loadChildren: '../topics/topics.module#TopicsModule'
          // },
          // {
          //   path: 'terms',
          //   loadChildren: '../terms-meaning/terms-meaning.module#TermsMeaningModule'
          // },
          // {
          //   path: 'hardware-types',
          //   loadChildren: '../hardware-types/hardware-types.module#HardwareTypesModule'
          // }
          ,
          {
            path: 'profile',
            loadChildren: '../profile/profile.module#ProfileModule'
          }
        ]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {
}
