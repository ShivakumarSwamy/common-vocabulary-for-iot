import {Component, OnInit} from '@angular/core';
import {ManagerTopicsService} from '../service/manager-topics.service';
import {ResponseMessage} from '../../response/response-message';
import {TopicFormProvider} from '../provider/topic-form-provider';

@Component({
  selector: 'app-topic-create',
  templateUrl: './topic-create.component.html'
})
export class TopicCreateComponent implements OnInit {

  // pathControl = this.formBuilder.control('', Validators.required);
  // middlewareEndpointControl = this.formBuilder.control('', Validators.required);
  // dataTypeControl = this.formBuilder.control('', Validators.required);
  // protocolControl = this.formBuilder.control('', Validators.required);
  // topicTypeControl = this.formBuilder.control('', Validators.required);
  // hardwareTypeControl = this.formBuilder.control('', Validators.required);
  // unitControl = this.formBuilder.control('', Validators.required);
  // messageFormatControl = this.formBuilder.control('', Validators.required);
  // metaModelTypeControl = this.formBuilder.control('', Validators.required);
  // metaModelControl = this.formBuilder.control('', Validators.required);
  //
  // topicsForm = this.formBuilder.group({
  //     path: this.pathControl,
  //     middlewareEndpoint: this.middlewareEndpointControl,
  //     topicType: this.topicTypeControl,
  //     protocol: this.protocolControl,
  //     dataType: this.dataTypeControl,
  //     hardwareType: this.hardwareTypeControl,
  //     unit: this.unitControl,
  //     messageFormat: this.messageFormatControl,
  //     metaModelType: this.metaModelTypeControl,
  //     metaModel: this.metaModelControl
  //   }, {updateOn: 'blur'}
  // );
  //
  // topicTypes = ['Publish', 'Subscribe'];
  // protocols = ['HTTP', 'MQTT'];
  // dataTypes = ['String', 'Integer', 'Float', 'Boolean', 'Double'];
  // messageFormats = ['JSON'];
  // metaModelTypes = ['JSON Schema'];
  //
  // pathValue = '';
  // middlewareEndpointValue = '';
  // dataTypeValue = '';
  // protocolValue = '';
  // topicTypeValue = '';
  // hardwareTypeValue = '';
  // unitValue = '';
  // messageFormatValue = '';
  // metaModelTypeValue = '';
  // metaModelValue = '';

  successResponseMessage: ResponseMessage;
  errorResponseMessage: ResponseMessage;

  constructor(private topicFormProvider: TopicFormProvider,
              private topicsService: ManagerTopicsService) {
  }

  ngOnInit() {

    this.topicFormProvider.subscribeAllControls();
    this.topicFormProvider.topicsForm.reset();
    this.topicFormProvider.initializeWithDefaultValuesForControls();
  }


  onSubmit() {
    this.clearSuccessResponseMessage();
    this.clearErrorResponseMessage();
    this.topicsService.createTopic(this.topicFormProvider.getTopicDTO())
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
