export interface EntityProperties {
  id: string;
  owner: string;
  path: string;
  protocol: string;
  middleware_endpoint: string;
  topic_type: string;
  data_type: string;

  component_type: string;
  unit: string;

  message_format: string;
  meta_model_type: string;
  meta_model: string;

  country: string,
  state: string,
  city: string,
  street: string
}
