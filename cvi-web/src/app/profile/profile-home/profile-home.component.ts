import {Component, OnInit} from '@angular/core';
import {UserDetails} from '../../response/user-details';
import {UserService} from "../../service/user.service";
import {ResultsSet} from "../../response/results-set";

@Component({
  selector: 'app-profile-home',
  templateUrl: './profile-home.component.html'
})
export class ProfileHomeComponent implements OnInit {

  private userDetailsResultsSet: ResultsSet<UserDetails>;
  private userDetails: UserDetails;

  private showErrorMessage = false;

  constructor(private usersService: UserService) {
  }

  ngOnInit() {
    this.usersService.read()
      .subscribe(
        value => {
          this.userDetailsResultsSet = value;
          this.updateUserDetails();
        },
        () => this.showErrorMessage = true
      );
  }

  updateUserDetails() {
    if (this.isResultsSetValid()) {
      this.userDetails = this.userDetailsResultsSet.results[0];
      this.showErrorMessage = false;
    }
  }

  isResultsSetValid() {
    return this.userDetailsResultsSet && this.userDetailsResultsSet.results && this.userDetailsResultsSet.results.length
  }
}
