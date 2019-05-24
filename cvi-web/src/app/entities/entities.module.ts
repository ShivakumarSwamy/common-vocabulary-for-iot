import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {TopicsRoutingModule} from './topics-routing.module';
import {TopicCreateComponent} from './topic-create/topic-create.component';
import {TopicsHomeComponent} from './topics-home/topics-home.component';
import {TopicsDashboardComponent} from './topics-dashboard/topics-dashboard.component';
import {TopicsSearchComponent} from './topics-search/topics-search.component';
import {TopicEditComponent} from './topic-edit/topic-edit.component';
import {TopicItemDetailsComponent} from './topic-item-details/topic-item-details.component';
import {TopicsListComponent} from './topics-list/topics-list.component';
import {ReactiveFormsModule} from '@angular/forms';
import {TopicFormProvider} from './provider/topic-form-provider';
import {ConsumerTopicsService} from './service/consumer-topics.service';
import {ManagerTopicsService} from './service/manager-topics.service';
import {AdminTopicsService} from "./service/admin-topics.service";

@NgModule({
  declarations: [
    TopicCreateComponent,
    TopicsHomeComponent,
    TopicsDashboardComponent,
    TopicsSearchComponent,
    TopicEditComponent,
    TopicItemDetailsComponent,
    TopicsListComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TopicsRoutingModule
  ],
  providers: [
    TopicFormProvider,
    ConsumerTopicsService,
    ManagerTopicsService,
    AdminTopicsService
  ]
})
export class TopicsModule {
}
