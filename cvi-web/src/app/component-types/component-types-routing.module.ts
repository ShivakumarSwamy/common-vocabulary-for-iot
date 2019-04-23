import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ComponentTypeCreateComponent} from './component-types-create/component-type-create.component';
import {ComponentTypesHomeComponent} from './component-types-home/component-types-home.component';
import {ComponentTypesListComponent} from './component-types-list/component-types-list.component';
import {AuthGuard} from "../auth/auth.guard";

const routes: Routes = [
  {
    path: '',
    component: ComponentTypesHomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        children: [
          {
            path: 'create',
            component: ComponentTypeCreateComponent
          },
          {
            path: 'list',
            component: ComponentTypesListComponent
          },
          {
            path: '',
            redirectTo: 'list'
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
export class ComponentTypesRoutingModule {
}
