import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ComponentTypesRoutingModule} from './component-types-routing.module';
import {ComponentTypesListComponent} from './component-types-list/component-types-list.component';
import {ComponentTypeCreateComponent} from './component-types-create/component-type-create.component';
import {ComponentTypesItemDetailsComponent} from './component-types-item-details/component-types-item-details.component';
import {ComponentTypesHomeComponent} from './component-types-home/component-types-home.component';
import {ComponentTypesDashboardComponent} from './component-types-dashboard/component-types-dashboard.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    ComponentTypesHomeComponent,
    ComponentTypesDashboardComponent,
    ComponentTypeCreateComponent,
    ComponentTypesListComponent,
    ComponentTypesItemDetailsComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    ComponentTypesRoutingModule
  ]
})
export class ComponentTypesModule {
}
