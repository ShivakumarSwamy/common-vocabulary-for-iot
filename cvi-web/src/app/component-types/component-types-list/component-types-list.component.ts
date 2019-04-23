import {Component, OnInit} from '@angular/core';
import {CviService} from '../../service/cvi.service';
import {ResultsSet} from "../../response/results-set";
import {ComponentTypeItemDetails} from "../../response/component-type-item-details";

@Component({
  selector: 'app-component-types-list',
  templateUrl: './component-types-list.component.html'
})
export class ComponentTypesListComponent implements OnInit {

  componentTypeItemDetailsResultsSet: ResultsSet<ComponentTypeItemDetails>;
  showCreateComponentType = false;

  constructor(private cviService: CviService) {
  }

  ngOnInit() {
    this.cviService.getAllComponentTypes()
      .subscribe(
        value => {
          this.componentTypeItemDetailsResultsSet = value;
          this.showOrHideCreateComponentType();
        }
      );
  }

  showOrHideCreateComponentType() {
    this.showCreateComponentType = !this.isResultsSetValid();
  }

  isResultsSetValid() {
    return this.componentTypeItemDetailsResultsSet &&
      this.componentTypeItemDetailsResultsSet.results &&
      this.componentTypeItemDetailsResultsSet.results.length;
  }

}
