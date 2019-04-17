import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HardwareTypesCreateComponent} from './hardware-types-create/hardware-types-create.component';
import {HardwareTypesHomeComponent} from './hardware-types-home/hardware-types-home.component';
import {HardwareTypesListComponent} from './hardware-types-list/hardware-types-list.component';
import {AuthGuard} from "../auth/auth.guard";

const routes: Routes = [
  {
    path: '',
    component: HardwareTypesHomeComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: '',
        children: [
          {
            path: 'create',
            component: HardwareTypesCreateComponent
          },
          {
            path: 'list',
            component: HardwareTypesListComponent
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
export class HardwareTypesRoutingModule {
}
