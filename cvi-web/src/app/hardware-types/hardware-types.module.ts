import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HardwareTypesRoutingModule} from './hardware-types-routing.module';
import {HardwareTypesListComponent} from './hardware-types-list/hardware-types-list.component';
import {HardwareTypesCreateComponent} from './hardware-types-create/hardware-types-create.component';
import {HardwareTypesItemDetailsComponent} from './hardware-types-item-details/hardware-types-item-details.component';
import {HardwareTypesHomeComponent} from './hardware-types-home/hardware-types-home.component';
import {HardwareTypesDashboardComponent} from './hardware-types-dashboard/hardware-types-dashboard.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    HardwareTypesHomeComponent,
    HardwareTypesDashboardComponent,
    HardwareTypesCreateComponent,
    HardwareTypesListComponent,
    HardwareTypesItemDetailsComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HardwareTypesRoutingModule
  ]
})
export class HardwareTypesModule {
}
