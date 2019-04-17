import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CviService } from '../../service/cvi.service';
import { TermsMeaningResultsSet } from '../../response/terms-meaning-results-set';
import { distinct } from 'rxjs/operators';

@Component({
  selector: 'app-terms-search',
  templateUrl: './terms-search.component.html'
})
export class TermsSearchComponent implements OnInit {
  searchControl = this.formBuilder.control('', Validators.required);
  termsText = '';
  showSorryMessage = false;

  termsMeaningResultsSet: TermsMeaningResultsSet;

  constructor(private formBuilder: FormBuilder,
              private cviService: CviService) {
  }

  ngOnInit(): void {
    this.searchControl.valueChanges
      .subscribe(
        (value: string) => this.termsText = value
      );
  }

  isExactResultsSetValid(): boolean {
    return !!(this.termsMeaningResultsSet && this.termsMeaningResultsSet.exactResults && this.termsMeaningResultsSet.exactResults.length);
  }

  isRelatedResultsSetValid(): boolean {
    return !!(this.termsMeaningResultsSet && this.termsMeaningResultsSet.relatedResults && this.termsMeaningResultsSet.relatedResults.length);
  }

  getResultsSet() {
    this.clearShowSorryMessage();

    if (this.isTermsTextValid()) {
      this.cviService.getMeaningTermsSearch(this.replaceTermsTextWhitespacesWithPlusSign())
        .subscribe(
          (tmr: TermsMeaningResultsSet) => {
            this.termsMeaningResultsSet = tmr;
            this.showSorryMessageOrNot();
          }
        );
    }
  }

  clearShowSorryMessage() {
    this.showSorryMessage = false;
  }

  showSorryMessageOrNot() {
    this.showSorryMessage = !(this.isExactResultsSetValid() || this.isRelatedResultsSetValid());
  }

  isTermsTextValid() {
    return this.termsText && this.termsText !== '';
  }

  replaceTermsTextWhitespacesWithPlusSign() {
    return this.termsText.replace(/\s+/g, '+').toLowerCase();
  }
}
