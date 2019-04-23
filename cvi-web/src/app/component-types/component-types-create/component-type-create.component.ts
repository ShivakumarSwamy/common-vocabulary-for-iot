import {Component, OnInit} from '@angular/core';
import {AbstractControlOptions, FormBuilder, Validators} from '@angular/forms';
import {ComponentTypeDto} from '../../dto/component-type-dto';
import {CviService} from '../../service/cvi.service';
import {ResponseMessage} from '../../response/response-message';


@Component({
  selector: 'app-component-types-create',
  templateUrl: './component-type-create.component.html'
})
export class ComponentTypeCreateComponent implements OnInit {

  defaultSensorCategory = 'Parking Space Sensor';
  defaultActuatorCategory = 'Parking Gate Actuator';

  changeAndRequired: AbstractControlOptions = {validators: Validators.required, updateOn: 'change'};

  componentControl = this.fmb.control('', this.changeAndRequired);
  categoryControl = this.fmb.control('', this.changeAndRequired);
  labelControl = this.fmb.control('', Validators.required);
  commentControl = this.fmb.control('', Validators.required);

  chtForm = this.fmb.group({
      component: this.componentControl,
      category: this.categoryControl,
      label: this.labelControl,
      comment: this.commentControl,
    }, {updateOn: 'blur'}
  );

  components = ['Sensor', 'Actuator'];
  categories = [this.defaultSensorCategory];

  componentValue = '';
  categoryValue = '';
  labelValue = '';
  commentValue = '';

  successResponseMessage: ResponseMessage;
  errorResponseMessage: ResponseMessage;

  constructor(private fmb: FormBuilder,
              private cviService: CviService) {
  }

  ngOnInit() {
    this.subscribeToComponentControl();
    this.subscribeToCategoryControl();
    this.subscribeToLabelControl();
    this.subscribeToCommentControl();
    this.initializeWithDefaultValuesForControls();
  }

  subscribeToComponentControl() {
    this.componentControl.valueChanges
      .subscribe(
        value => {
          this.componentValue = value;
          this.updateCategoriesAndControl();
        }
      );
  }

  initializeWithDefaultValuesForControls() {
    this.componentControl.setValue('Sensor');
    this.categoryControl.setValue(this.defaultSensorCategory);
  }

  updateCategoriesAndControl() {
    if (this.componentValue && this.componentValue === 'Sensor') {
      this.categoryControl.setValue(this.defaultSensorCategory);
      this.categories = [this.defaultSensorCategory];
    } else if (this.componentValue && this.componentValue === 'Actuator') {
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

  getCustomComponentTypeDTO(): ComponentTypeDto {
    return {
      component: this.componentValue,
      category: this.categoryValue,
      label: this.labelValue,
      comment: this.commentValue
    };
  }

  onSubmit() {
    this.clearSuccessResponseMessage();
    this.clearErrorResponseMessage();
    this.cviService.createComponentType(this.getCustomComponentTypeDTO())
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
