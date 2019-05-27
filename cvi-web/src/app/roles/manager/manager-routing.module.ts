import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ManagerHomeComponent} from './manager-home/manager-home.component';
import {AuthGuard} from '../../auth/auth.guard';
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
          },
          {
            path: 'entities',
            loadChildren: '../../entities/entities.module#EntitiesModule'
          },
          {
            path: 'terms',
            loadChildren: '../../terms-meaning/terms-meaning.module#TermsMeaningModule'
          },
          {
            path: 'component-types',
            loadChildren: '../../component-types/component-types.module#ComponentTypesModule'
          },
          {
            path: 'profile',
            loadChildren: '../../profile/profile.module#ProfileModule'
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
