import {Component, OnInit} from '@angular/core';
import {CviService} from '../../service/cvi.service';
import {ResultsSet} from "../../response/results-set";
import {HardwareTypeItemDetails} from "../../response/hardware-type-item-details";

@Component({
  selector: 'app-hardware-types-list',
  templateUrl: './hardware-types-list.component.html'
})
export class HardwareTypesListComponent implements OnInit {

  hardwareTypeItemDetailsResultsSet: ResultsSet<HardwareTypeItemDetails>;
  showCreateHardwareType = false;

  constructor(private cviService: CviService) {
  }

  ngOnInit() {
    this.cviService.getAllHardwareTypes()
      .subscribe(
        value => {
          this.hardwareTypeItemDetailsResultsSet = value;
          this.showOrHideCreateHardwareType();
        }
      );
  }

  showOrHideCreateHardwareType() {
    this.showCreateHardwareType = !this.isResultsSetValid();
  }

  isResultsSetValid() {
    return this.hardwareTypeItemDetailsResultsSet &&
      this.hardwareTypeItemDetailsResultsSet.results &&
      this.hardwareTypeItemDetailsResultsSet.results.length;
  }

}
