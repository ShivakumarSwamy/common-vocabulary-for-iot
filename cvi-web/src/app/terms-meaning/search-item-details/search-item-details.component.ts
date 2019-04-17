import {Component, Input} from '@angular/core';
import {SearchItemDetails} from '../../response/search-item-details';

@Component({
  selector: 'app-search-item-details',
  templateUrl: './search-item-details.component.html'
})
export class SearchItemDetailsComponent {

  @Input() searchItemDetails: SearchItemDetails;

}
