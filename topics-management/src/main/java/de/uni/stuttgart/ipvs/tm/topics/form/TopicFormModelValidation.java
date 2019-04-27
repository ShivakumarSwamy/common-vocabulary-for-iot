package de.uni.stuttgart.ipvs.tm.topics.form;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

import de.uni.stuttgart.ipvs.form.control.FormControl;
import de.uni.stuttgart.ipvs.form.control.FormControlImpl;
import de.uni.stuttgart.ipvs.form.model.FormModel;
import de.uni.stuttgart.ipvs.form.model.FormModelImpl;
import de.uni.stuttgart.ipvs.form.validation.FormControlError;
import de.uni.stuttgart.ipvs.form.validation.FormModelValidator;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicCreateDTO;
import de.uni.stuttgart.ipvs.tm.topics.dto.TopicEditDTO;
import de.uni.stuttgart.ipvs.tm.topics.properties.HardwareProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.LocationProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.MessageProperties;
import de.uni.stuttgart.ipvs.tm.topics.properties.TopicProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.tm.topics.form.TopicFormControlIds.*;

@Component
@Slf4j
public class TopicFormModelValidation {

    private final FormModelValidator tFMV;

    @Autowired
    public TopicFormModelValidation(@Qualifier("topicFormModel") FormModelValidator tFMV) {
        this.tFMV = tFMV;
    }

    private static void throwTopicFormControlErrorException(FormControlError formControlError) {
        log.error("FORM CONTROL ERROR: " + formControlError.toString());
        throw new TopicFormControlErrorException(formControlError.getHelp());
    }

    private static FormModel formModel(TopicCreateDTO topicCreateDTO) {
        return new FormModelImpl(formControls(topicCreateDTO));
    }

    private static FormModel formModel(TopicEditDTO topicEditDTO) {
        return new FormModelImpl(formControls(topicEditDTO));
    }

    private static Collection<FormControl> formControls(TopicEditDTO topicEditDTO) {
        var formControls = new ArrayList<>(formControlsTopicProperties(topicEditDTO));

        formControls.addAll(formControlsMessageProperties(topicEditDTO));
        formControls.addAll(formControlsHardwareProperties(topicEditDTO));
        formControls.addAll(formControlsLocationProperties(topicEditDTO));

        return formControls;
    }

    private static Collection<FormControl> formControls(TopicCreateDTO topicCreateDTO) {
        var formControls = new ArrayList<>(formControlsTopicProperties(topicCreateDTO));

        formControls.addAll(formControlsMessageProperties(topicCreateDTO));
        formControls.addAll(formControlsHardwareProperties(topicCreateDTO));
        formControls.addAll(formControlsLocationProperties(topicCreateDTO));

        return formControls;
    }

    private static Collection<FormControl> formControlsLocationProperties(LocationProperties locationProperties) {

        var countryFormControl = new FormControlImpl<>(COUNTRY, locationProperties.getCountry());
        var stateFormControl = new FormControlImpl<>(STATE, locationProperties.getState());
        var cityFormControl = new FormControlImpl<>(CITY, locationProperties.getCity());
        var streetFormControl = new FormControlImpl<>(STREET, locationProperties.getStreet());
        var pointFormControl = new FormControlImpl<>(POINT, locationProperties.getPoint());

        return List.of(countryFormControl, stateFormControl, cityFormControl, streetFormControl, pointFormControl);
    }

    private static Collection<FormControl> formControlsMessageProperties(MessageProperties messageProperties) {

        var messageFormatFormControl = new FormControlImpl<>(MESSAGE_FORMAT, messageProperties.getMessageFormat());
        var metaModelFormControl = new FormControlImpl<>(META_MODEL, messageProperties.getMetaModel());
        var metaModelTypeFormControl = new FormControlImpl<>(META_MODEL_TYPE, messageProperties.getMetaModelType());

        return List.of(messageFormatFormControl, metaModelFormControl, metaModelTypeFormControl);
    }

    private static Collection<FormControl> formControlsTopicProperties(TopicProperties topicProperties) {

        var pathFormControl = new FormControlImpl<>(PATH, topicProperties.getPath());
        var middlewareEndpointFormControl = new FormControlImpl<>(MIDDLEWARE_ENDPOINT, topicProperties.getMiddlewareEndpoint());
        var topicTypeControl = new FormControlImpl<>(TOPIC_TYPE, topicProperties.getTopicType());
        var protocolControl = new FormControlImpl<>(PROTOCOL, topicProperties.getProtocol());
        var dataTypeControl = new FormControlImpl<>(DATA_TYPE, topicProperties.getDataType());

        return List.of(pathFormControl, middlewareEndpointFormControl,
                topicTypeControl, protocolControl, dataTypeControl);
    }

    private static Collection<FormControl> formControlsHardwareProperties(HardwareProperties hardwareProperties) {

        var hardwareTypeFormControl = new FormControlImpl<>(HARDWARE_TYPE, hardwareProperties.getHardwareType());
        var unitFormControl = new FormControlImpl<>(UNIT, hardwareProperties.getUnit());

        return List.of(hardwareTypeFormControl, unitFormControl);
    }

    public void validate(TopicCreateDTO topicCreateDTO) {
        var formModel = formModel(topicCreateDTO);
        validateFormModelUsingFormModelValidator(formModel, "TOPIC CREATE");
    }

    public void validate(TopicEditDTO topicEditDTO) {

        if (topicEditDTO.getOwner() == null || topicEditDTO.getOwner().isEmpty())
            throw new TopicFormControlErrorException("Owner id is required");

        var formModel = formModel(topicEditDTO);
        validateFormModelUsingFormModelValidator(formModel, "TOPIC EDIT");
    }

    private void validateFormModelUsingFormModelValidator(FormModel formModel, String formName) {
        if (!this.tFMV.isValid(formModel)) {
            throwTopicFormControlErrorException(this.tFMV.getError());
        }
        log.debug( formName + " FORM IS VALID");
    }
}
