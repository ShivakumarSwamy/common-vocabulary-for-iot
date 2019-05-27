import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {EntitiesRoutingModule} from './entities-routing.module';
import {EntityCreateComponent} from './entity-create/entity-create.component';
import {EntitiesHomeComponent} from './entities-home/entities-home.component';
import {EntitiesDashboardComponent} from './entities-dashboard/entities-dashboard.component';
import {EntitiesSearchComponent} from './entities-search/entities-search.component';
import {EntityEditComponent} from './entity-edit/entity-edit.component';
import {EntityItemDetailsComponent} from './entity-item-details/entity-item-details.component';
import {EntitiesListComponent} from './entities-list/entities-list.component';
import {ReactiveFormsModule} from '@angular/forms';
import {EntityFormProvider} from './provider/entity-form-provider';
import {ConsumerEntitiesService} from './service/consumer-entities.service';
import {ManagerEntitiesService} from './service/manager-entities.service';
import {AdminEntitiesService} from "./service/admin-entities.service";

@NgModule({
  declarations: [
    EntityCreateComponent,
    EntitiesHomeComponent,
    EntitiesDashboardComponent,
    EntitiesSearchComponent,
    EntityEditComponent,
    EntityItemDetailsComponent,
    EntitiesListComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    EntitiesRoutingModule
  ],
  providers: [
    EntityFormProvider,
    ConsumerEntitiesService,
    ManagerEntitiesService,
    AdminEntitiesService
  ]
})
export class EntitiesModule {
}
