import { Component, OnInit } from '@angular/core';
import {UserService} from "../../service/user.service";
import {ResultsSet} from "../../response/results-set";
import {UserDetails} from "../../response/user-details";

@Component({
  selector: 'app-admin-users-list',
  templateUrl: './admin-users-list.component.html'
})
export class AdminUsersListComponent implements OnInit {

  resultsSetUserDetails: ResultsSet<UserDetails>;

  constructor(private usersService: UserService) { }

  ngOnInit() {
    this.usersService.readAll()
      .subscribe(
        resultsSet => this.resultsSetUserDetails = resultsSet
      );
  }


  isResultsSetValid() {
    return this.resultsSetUserDetails && this.resultsSetUserDetails.results && this.resultsSetUserDetails.results.length;
  }
}
