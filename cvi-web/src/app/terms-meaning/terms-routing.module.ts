import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TermsSearchComponent} from './terms-meaning-search/terms-search.component';
import {AuthGuard} from "../auth/auth.guard";

const routes: Routes = [
  {
    path: '',
    component: TermsSearchComponent,
    canActivate: [AuthGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TermsRoutingModule {
}
