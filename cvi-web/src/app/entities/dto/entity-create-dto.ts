export interface EntityCreateDto {
  path: string;
  protocol: string;
  middlewareEndpoint: string;
  topicType: string;
  dataType: string;

  componentType: string;
  unit: string;

  messageFormat: string;
  metaModelType: string;
  metaModel: string;

  country: string;
  state: string;
  city: string;
  street: string;
}
