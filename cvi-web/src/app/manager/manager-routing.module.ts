import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManagerHomeComponent} from './manager-home/manager-home.component';
import {AuthGuard} from '../auth/auth.guard';
import {ManagerWelcomeComponent} from './manager-welcome/manager-welcome.component';

const routes: Routes = [
  {
    path: 'manager',
    component: ManagerHomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        canActivateChild: [AuthGuard],
        children: [
          {
            path: '',
            component: ManagerWelcomeComponent
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
export class ManagerRoutingModule {
}
