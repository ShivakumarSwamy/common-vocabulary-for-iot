import {FormBuilder, Validators} from '@angular/forms';
import {Injectable} from '@angular/core';
import {EntityCreateDto} from '../dto/entity-create-dto';
import {EntityProperties} from '../response/entity-properties';
import {EntityEditDto} from "../dto/entity-edit-dto";

@Injectable()
export class EntityFormProvider {

  pathControl = this.formBuilder.control('', Validators.required);
  middlewareEndpointControl = this.formBuilder.control('', Validators.required);
  dataTypeControl = this.formBuilder.control('', Validators.required);
  protocolControl = this.formBuilder.control('', Validators.required);
  topicTypeControl = this.formBuilder.control('', Validators.required);

  componentTypeControl = this.formBuilder.control('', Validators.required);
  unitControl = this.formBuilder.control('', Validators.required);

  messageFormatControl = this.formBuilder.control('', Validators.required);
  metaModelTypeControl = this.formBuilder.control('', Validators.required);
  metaModelControl = this.formBuilder.control('', Validators.required);

  countyControl = this.formBuilder.control('', Validators.required);
  stateControl = this.formBuilder.control('', Validators.required);
  cityControl = this.formBuilder.control('', Validators.required);
  streetControl = this.formBuilder.control('', Validators.required);

  entityForm = this.formBuilder.group({
      path: this.pathControl,
      middlewareEndpoint: this.middlewareEndpointControl,
      topicType: this.topicTypeControl,
      protocol: this.protocolControl,
      dataType: this.dataTypeControl,

      componentType: this.componentTypeControl,
      unit: this.unitControl,

      messageFormat: this.messageFormatControl,
      metaModelType: this.metaModelTypeControl,
      metaModel: this.metaModelControl,

      country: this.countyControl,
      state: this.stateControl,
      city: this.cityControl,
      street: this.streetControl,
    }, {updateOn: 'blur'}
  );

  idControl = this.formBuilder.control('', Validators.required);
  ownerControl = this.formBuilder.control('', Validators.required);

  topicTypes = ['Publish', 'Subscribe'];
  protocols = ['HTTP', 'MQTT'];
  dataTypes = ['string', 'boolean'];
  messageFormats = ['JSON'];
  metaModelTypes = ['JSON Schema'];

  pathValue = '';
  middlewareEndpointValue = '';
  dataTypeValue = '';
  protocolValue = '';
  topicTypeValue = '';
  componentTypeValue = '';
  unitValue = '';
  messageFormatValue = '';
  metaModelTypeValue = '';
  metaModelValue = '';
  countryValue = '';
  stateValue = '';
  cityValue = '';
  streetValue = '';

  ownerValue = '';

  constructor(private formBuilder: FormBuilder) {
  }

  subscribeAllEditControls() {
    this.subscribeAllCreateControls();
    this.subscribeToOwnerControl();
  }

  subscribeAllCreateControls() {
    this.subscribeToPathControl();
    this.subscribeToMiddlewareEndpointControl();
    this.subscribeToTopicTypeControl();
    this.subscribeToProtocolControl();
    this.subscribeToDataTypeControl();

    this.subscribeToComponentTypeControl();
    this.subscribeToUnitControl();

    this.subscribeToMessageFormatControl();
    this.subscribeToMetaModelTypeControl();
    this.subscribeToMetaModelControl();

    this.subscribeToCountryControl();
    this.subscribeToStateControl();
    this.subscribeToCityControl();
    this.subscribeToStreetControl();
  }

  setControlValuesUsingEntityProperties(entityProperties: EntityProperties) {
    this.pathControl.setValue(entityProperties.path);
    this.middlewareEndpointControl.setValue(entityProperties.middleware_endpoint);
    this.topicTypeControl.setValue(entityProperties.topic_type);
    this.protocolControl.setValue(entityProperties.protocol);
    this.dataTypeControl.setValue(entityProperties.data_type);

    this.componentTypeControl.setValue(entityProperties.component_type);
    this.unitControl.setValue(entityProperties.unit);

    this.messageFormatControl.setValue(entityProperties.message_format);
    this.metaModelTypeControl.setValue(entityProperties.meta_model_type);
    this.metaModelControl.setValue(entityProperties.meta_model);

    this.countyControl.setValue(entityProperties.country);
    this.stateControl.setValue(entityProperties.state);
    this.cityControl.setValue(entityProperties.city);
    this.streetControl.setValue(entityProperties.street);

    this.idControl.setValue(entityProperties.id);
    this.ownerControl.setValue(entityProperties.owner);
  }

  initializeWithDefaultValuesForControls() {
    this.topicTypeControl.setValue('Publish');
    this.protocolControl.setValue('HTTP');
    this.dataTypeControl.setValue('string');
    this.messageFormatControl.setValue('JSON');
    this.metaModelTypeControl.setValue('JSON Schema');
  }


  subscribeToOwnerControl() {
    this.ownerControl.valueChanges
      .subscribe(
        value => this.ownerValue = value
      );
  }

  subscribeToPathControl() {
    this.pathControl.valueChanges
      .subscribe(
        value => this.pathValue = value
      );
  }

  subscribeToMiddlewareEndpointControl() {
    this.middlewareEndpointControl.valueChanges
      .subscribe(
        value => this.middlewareEndpointValue = value
      );
  }

  subscribeToDataTypeControl() {
    this.dataTypeControl.valueChanges
      .subscribe(
        value => this.dataTypeValue = value
      );
  }

  subscribeToProtocolControl() {
    this.protocolControl.valueChanges
      .subscribe(
        value => this.protocolValue = value
      );
  }

  subscribeToTopicTypeControl() {
    this.topicTypeControl.valueChanges
      .subscribe(
        value => this.topicTypeValue = value
      );
  }

  subscribeToComponentTypeControl() {
    this.componentTypeControl.valueChanges
      .subscribe(
        value => this.componentTypeValue = value
      );
  }

  subscribeToUnitControl() {
    this.unitControl.valueChanges
      .subscribe(
        value => this.unitValue = value
      );
  }

  subscribeToMessageFormatControl() {
    this.messageFormatControl.valueChanges
      .subscribe(
        value => this.messageFormatValue = value
      );
  }

  subscribeToMetaModelTypeControl() {
    this.metaModelTypeControl.valueChanges
      .subscribe(
        value => this.metaModelTypeValue = value
      );
  }

  subscribeToMetaModelControl() {
    this.metaModelControl.valueChanges
      .subscribe(
        value => this.metaModelValue = value
      );
  }

  subscribeToCountryControl() {
    this.countyControl.valueChanges
      .subscribe(
        value => this.countryValue = value
      );
  }

  subscribeToStateControl() {
    this.stateControl.valueChanges
      .subscribe(
        value => this.stateValue = value
      );
  }

  subscribeToCityControl() {
    this.cityControl.valueChanges
      .subscribe(
        value => this.cityValue = value
      );
  }

  subscribeToStreetControl() {
    this.streetControl.valueChanges
      .subscribe(
        value => this.streetValue = value
      );
  }


  getEntityCreateDto(): EntityCreateDto {
    return {
      path: this.pathValue,
      middlewareEndpoint: this.middlewareEndpointValue,
      dataType: this.dataTypeValue,
      protocol: this.protocolValue,
      topicType: this.topicTypeValue,

      componentType: this.componentTypeValue,
      unit: this.unitValue,

      messageFormat: this.messageFormatValue,
      metaModelType: this.metaModelTypeValue,
      metaModel: this.escapeDoubleQuotes(),

      country: this.countryValue,
      state: this.stateValue,
      city: this.cityValue,
      street: this.streetValue
    };
  }

  getEntityEditDto(): EntityEditDto {
    return {
      owner: this.ownerValue,
      path: this.pathValue,
      middlewareEndpoint: this.middlewareEndpointValue,
      dataType: this.dataTypeValue,
      protocol: this.protocolValue,
      topicType: this.topicTypeValue,

      componentType: this.componentTypeValue,
      unit: this.unitValue,

      messageFormat: this.messageFormatValue,
      metaModelType: this.metaModelTypeValue,
      metaModel: this.escapeDoubleQuotes(),

      country: this.countryValue,
      state: this.stateValue,
      city: this.cityValue,
      street: this.streetValue
    };
  }

  escapeDoubleQuotes() {
    if (this.messageFormatValue === 'JSON') {
      return this.metaModelValue.trim().replace(/"/g, '\\"');
    }
    return this.metaModelValue;
  }
}
