import {Component, OnInit} from '@angular/core';
import {AbstractControlOptions, FormBuilder, Validators} from '@angular/forms';
import {HardwareTypeDto} from '../../dto/hardware-type-dto';
import {CviService} from '../../service/cvi.service';
import {ResponseMessage} from '../../response/response-message';


@Component({
  selector: 'app-hardware-types-create',
  templateUrl: './hardware-types-create.component.html'
})
export class HardwareTypesCreateComponent implements OnInit {

  defaultSensorCategory = 'Parking Space Sensor';
  defaultActuatorCategory = 'Parking Gate Actuator';

  changeAndRequired: AbstractControlOptions = {validators: Validators.required, updateOn: 'change'};

  hardwareComponentControl = this.fmb.control('', this.changeAndRequired);
  categoryControl = this.fmb.control('', this.changeAndRequired);
  labelControl = this.fmb.control('', Validators.required);
  commentControl = this.fmb.control('', Validators.required);

  chtForm = this.fmb.group({
      hardwareComponent: this.hardwareComponentControl,
      category: this.categoryControl,
      label: this.labelControl,
      comment: this.commentControl,
    }, {updateOn: 'blur'}
  );

  hardwareComponents = ['Sensor', 'Actuator'];
  categories = [this.defaultSensorCategory];

  hardwareComponentValue = '';
  categoryValue = '';
  labelValue = '';
  commentValue = '';

  successResponseMessage: ResponseMessage;
  errorResponseMessage: ResponseMessage;

  constructor(private fmb: FormBuilder,
              private cviService: CviService) {
  }

  ngOnInit() {
    this.subscribeToHardwareComponentControl();
    this.subscribeToCategoryControl();
    this.subscribeToLabelControl();
    this.subscribeToCommentControl();
    this.initializeWithDefaultValuesForControls();
  }

  subscribeToHardwareComponentControl() {
    this.hardwareComponentControl.valueChanges
      .subscribe(
        value => {
          this.hardwareComponentValue = value;
          this.updateCategoriesAndControl();
        }
      );
  }

  initializeWithDefaultValuesForControls() {
    this.hardwareComponentControl.setValue('Sensor');
    this.categoryControl.setValue(this.defaultSensorCategory);
  }

  updateCategoriesAndControl() {
    if (this.hardwareComponentValue && this.hardwareComponentValue === 'Sensor') {
      this.categoryControl.setValue(this.defaultSensorCategory);
      this.categories = [this.defaultSensorCategory];
    } else if (this.hardwareComponentValue && this.hardwareComponentValue === 'Actuator') {
      this.categoryControl.setValue(this.defaultActuatorCategory);
      this.categories = [this.defaultActuatorCategory];
    } else {
      this.categoryControl.setValue(this.defaultSensorCategory);
      this.categories = [this.defaultSensorCategory];
    }
  }

  subscribeToCategoryControl() {
    this.categoryControl.valueChanges
      .subscribe(
        value => this.categoryValue = value
      )
    ;
  }

  subscribeToLabelControl() {
    this.labelControl.valueChanges
      .subscribe(
        value => this.labelValue = value
      );
  }

  subscribeToCommentControl() {
    this.commentControl.valueChanges
      .subscribe(
        value => this.commentValue = value
      );
  }

  getCustomHardwareTypeDTO(): HardwareTypeDto {
    return {
      hardwareComponent: this.hardwareComponentValue,
      category: this.categoryValue,
      label: this.labelValue,
      comment: this.commentValue
    };
  }

  onSubmit() {
    this.clearSuccessResponseMessage();
    this.clearErrorResponseMessage();
    this.cviService.createHardwareType(this.getCustomHardwareTypeDTO())
      .subscribe(
        srm => this.successResponseMessage = srm,
        erm => this.errorResponseMessage = erm,
      );
  }

  clearSuccessResponseMessage() {
    this.successResponseMessage = undefined;
  }

  clearErrorResponseMessage() {
    this.errorResponseMessage = undefined;
  }
}
