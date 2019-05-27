import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EntityCreateComponent} from './entity-create/entity-create.component';
import {EntitiesSearchComponent} from './entities-search/entities-search.component';
import {EntitiesListComponent} from './entities-list/entities-list.component';
import {EntityEditComponent} from './entity-edit/entity-edit.component';
import {EntitiesHomeComponent} from './entities-home/entities-home.component';
import {AuthGuard} from '../auth/auth.guard';

const routes: Routes = [
  {
    path: '',
    component: EntitiesHomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        canActivate: [AuthGuard],
        children: [
          {
            path: 'search',
            component: EntitiesSearchComponent
          },
          {
            path: 'list',
            component: EntitiesListComponent
          },
          {
            path: 'create',
            component: EntityCreateComponent
          },
          {
            path: 'edit/:idValue',
            component: EntityEditComponent
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
export class EntitiesRoutingModule {
}
