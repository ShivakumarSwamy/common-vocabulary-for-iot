import {FormBuilder, Validators} from '@angular/forms';
import {Injectable} from '@angular/core';
import {TopicCreateDto} from '../dto/topic-create-dto';
import {TopicProperties} from '../response/topic-properties';
import {TopicEditDto} from "../dto/topic-edit-dto";

@Injectable()
export class TopicFormProvider {

  pathControl = this.formBuilder.control('', Validators.required);
  middlewareEndpointControl = this.formBuilder.control('', Validators.required);
  dataTypeControl = this.formBuilder.control('', Validators.required);
  protocolControl = this.formBuilder.control('', Validators.required);
  topicTypeControl = this.formBuilder.control('', Validators.required);
  hardwareTypeControl = this.formBuilder.control('', Validators.required);
  unitControl = this.formBuilder.control('', Validators.required);
  messageFormatControl = this.formBuilder.control('', Validators.required);
  metaModelTypeControl = this.formBuilder.control('', Validators.required);
  metaModelControl = this.formBuilder.control('', Validators.required);

  topicsForm = this.formBuilder.group({
      path: this.pathControl,
      middlewareEndpoint: this.middlewareEndpointControl,
      topicType: this.topicTypeControl,
      protocol: this.protocolControl,
      dataType: this.dataTypeControl,
      hardwareType: this.hardwareTypeControl,
      unit: this.unitControl,
      messageFormat: this.messageFormatControl,
      metaModelType: this.metaModelTypeControl,
      metaModel: this.metaModelControl
    }, {updateOn: 'blur'}
  );

  idControl = this.formBuilder.control('', Validators.required);
  ownerControl = this.formBuilder.control('', Validators.required);

  topicTypes = ['Publish', 'Subscribe'];
  protocols = ['HTTP', 'MQTT'];
  dataTypes = ['string', 'integer', 'float', 'boolean', 'double'];
  messageFormats = ['JSON'];
  metaModelTypes = ['JSON Schema'];

  pathValue = '';
  middlewareEndpointValue = '';
  dataTypeValue = '';
  protocolValue = '';
  topicTypeValue = '';
  hardwareTypeValue = '';
  unitValue = '';
  messageFormatValue = '';
  metaModelTypeValue = '';
  metaModelValue = '';

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
    this.subscribeToHardwareTypeControl();
    this.subscribeToUnitControl();
    this.subscribeToMessageFormatControl();
    this.subscribeToMetaModelTypeControl();
    this.subscribeToMetaModelControl();
  }

  setControlValuesUsingTopicProperties(topicProperties: TopicProperties) {
    this.pathControl.setValue(topicProperties.path);
    this.middlewareEndpointControl.setValue(topicProperties.middleware_endpoint);
    this.topicTypeControl.setValue(topicProperties.topic_type);
    this.protocolControl.setValue(topicProperties.protocol);
    this.dataTypeControl.setValue(topicProperties.data_type);

    this.hardwareTypeControl.setValue(topicProperties.hardware_type);
    this.unitControl.setValue(topicProperties.unit);

    this.messageFormatControl.setValue(topicProperties.message_format);
    this.metaModelTypeControl.setValue(topicProperties.meta_model_type);
    this.metaModelControl.setValue(topicProperties.meta_model);

    this.idControl.setValue(topicProperties.id);
    this.ownerControl.setValue(topicProperties.owner);
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

  subscribeToHardwareTypeControl() {
    this.hardwareTypeControl.valueChanges
      .subscribe(
        value => this.hardwareTypeValue = value
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

  getTopicCreateDto(): TopicCreateDto {
    return {
      path: this.pathValue,
      middlewareEndpoint: this.middlewareEndpointValue,
      dataType: this.dataTypeValue,
      protocol: this.protocolValue,
      topicType: this.topicTypeValue,
      hardwareType: this.hardwareTypeValue,
      unit: this.unitValue,
      messageFormat: this.messageFormatValue,
      metaModelType: this.metaModelTypeValue,
      metaModel: this.escapeDoubleQuotes()
    };
  }

  getTopicEditDto(): TopicEditDto {
    return {
      owner: this.ownerValue,
      path: this.pathValue,
      middlewareEndpoint: this.middlewareEndpointValue,
      dataType: this.dataTypeValue,
      protocol: this.protocolValue,
      topicType: this.topicTypeValue,
      hardwareType: this.hardwareTypeValue,
      unit: this.unitValue,
      messageFormat: this.messageFormatValue,
      metaModelType: this.metaModelTypeValue,
      metaModel: this.escapeDoubleQuotes()
    };
  }

  escapeDoubleQuotes() {
    if (this.messageFormatValue === 'JSON') {
      return this.metaModelValue.trim().replace(/"/g, '\\"');
    }
    return this.metaModelValue;
  }
}