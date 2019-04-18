import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ConsumerHomeComponent} from './consumer-home/consumer-home.component';
import {AuthGuard} from '../auth/auth.guard';
import {ConsumerWelcomeComponent} from './consumer-welcome/consumer-welcome.component';

const routes: Routes = [
  {
    path: 'consumer',
    component: ConsumerHomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        canActivateChild: [AuthGuard],
        children: [
          {
            path: '',
            component: ConsumerWelcomeComponent
          },
          {
            path: 'topics',
            loadChildren: '../topics/topics.module#TopicsModule'
          },
          {
            path: 'terms',
            loadChildren: '../terms-meaning/terms-meaning.module#TermsMeaningModule'
          },
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
export class ConsumerRoutingModule {
}
