import {Component, Input} from '@angular/core';
import {ComponentTypeItemDetails} from '../../response/component-type-item-details';

@Component({
  selector: 'app-component-types-item-details',
  templateUrl: './component-types-item-details.component.html'
})
export class ComponentTypesItemDetailsComponent {

  @Input() componentTypeItemDetails: ComponentTypeItemDetails;

}
