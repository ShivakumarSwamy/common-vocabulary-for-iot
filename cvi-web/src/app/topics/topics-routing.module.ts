import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TopicCreateComponent} from './topic-create/topic-create.component';
import {TopicsSearchComponent} from './topics-search/topics-search.component';
import {TopicsListComponent} from './topics-list/topics-list.component';
import {TopicEditComponent} from './topic-edit/topic-edit.component';
import {TopicsHomeComponent} from './topics-home/topics-home.component';
import {AuthGuard} from '../auth/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: TopicsHomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        canActivate: [AuthGuard],
        children: [
          {
            path: 'search',
            component: TopicsSearchComponent
          },
          {
            path: 'list',
            component: TopicsListComponent
          },
          {
            path: 'create',
            component: TopicCreateComponent
          },
          {
            path: 'edit/:id',
            component: TopicEditComponent
          },
          {
            path: '',
            redirectTo: 'search'
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
export class TopicsRoutingModule {
}
