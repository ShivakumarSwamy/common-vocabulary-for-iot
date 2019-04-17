import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {TermsRoutingModule} from './terms-routing.module';
import {TermsSearchComponent} from './terms-meaning-search/terms-search.component';
import {SearchItemDetailsComponent} from './search-item-details/search-item-details.component';
import {ReactiveFormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    TermsSearchComponent,
    SearchItemDetailsComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    TermsRoutingModule
  ]
})
export class TermsMeaningModule {
}
