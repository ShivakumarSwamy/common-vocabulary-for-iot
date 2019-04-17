import {Component, Input} from '@angular/core';
import {HardwareTypeItemDetails} from '../../response/hardware-type-item-details';

@Component({
  selector: 'app-hardware-types-item-details',
  templateUrl: './hardware-types-item-details.component.html'
})
export class HardwareTypesItemDetailsComponent {

  @Input() hardwareTypeItemDetails: HardwareTypeItemDetails;

}
