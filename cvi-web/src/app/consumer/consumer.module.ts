import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ConsumerRoutingModule } from './consumer-routing.module';
import { ConsumerHomeComponent } from './consumer-home/consumer-home.component';
import { ConsumerWelcomeComponent } from './consumer-welcome/consumer-welcome.component';
import { ConsumerToolbarComponent } from './consumer-toolbar/consumer-toolbar.component';

@NgModule({
  declarations: [ConsumerHomeComponent, ConsumerWelcomeComponent, ConsumerToolbarComponent],
  imports: [
    CommonModule,
    ConsumerRoutingModule
  ]
})
export class ConsumerModule { }
