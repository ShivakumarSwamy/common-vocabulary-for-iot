package de.uni.stuttgart.ipvs.em.entities.form;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

import de.uni.stuttgart.ipvs.em.entities.dto.EntityCreateDTO;
import de.uni.stuttgart.ipvs.em.entities.dto.EntityEditDTO;
import de.uni.stuttgart.ipvs.em.entities.properties.HardwareProperties;
import de.uni.stuttgart.ipvs.em.entities.properties.LocationProperties;
import de.uni.stuttgart.ipvs.em.entities.properties.MessageProperties;
import de.uni.stuttgart.ipvs.em.entities.properties.TopicProperties;
import de.uni.stuttgart.ipvs.form.control.FormControl;
import de.uni.stuttgart.ipvs.form.control.FormControlImpl;
import de.uni.stuttgart.ipvs.form.model.FormModel;
import de.uni.stuttgart.ipvs.form.model.FormModelImpl;
import de.uni.stuttgart.ipvs.form.validation.FormControlError;
import de.uni.stuttgart.ipvs.form.validation.FormModelValidator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.em.entities.form.EntityFormControlIds.*;

@Component
@Slf4j
public class EntityFormModelValidation {

    private final FormModelValidator tFMV;

    @Autowired
    public EntityFormModelValidation(@Qualifier("entityFormModel") FormModelValidator tFMV) {
        this.tFMV = tFMV;
    }

    private static void throwEntityFormControlErrorException(FormControlError formControlError) {
        log.error("FORM CONTROL ERROR: " + formControlError.toString());
        throw new EntityFormControlErrorException(formControlError.getHelp());
    }

    private static FormModel formModel(EntityCreateDTO entityCreateDTO) {
        return new FormModelImpl(formControls(entityCreateDTO));
    }

    private static FormModel formModel(EntityEditDTO entityEditDTO) {
        return new FormModelImpl(formControls(entityEditDTO));
    }

    private static Collection<FormControl> formControls(EntityEditDTO entityEditDTO) {
        var formControls = new ArrayList<>(formControlsTopicProperties(entityEditDTO));

        formControls.addAll(formControlsMessageProperties(entityEditDTO));
        formControls.addAll(formControlsHardwareProperties(entityEditDTO));
        formControls.addAll(formControlsLocationProperties(entityEditDTO));

        return formControls;
    }

    private static Collection<FormControl> formControls(EntityCreateDTO entityCreateDTO) {
        var formControls = new ArrayList<>(formControlsTopicProperties(entityCreateDTO));

        formControls.addAll(formControlsMessageProperties(entityCreateDTO));
        formControls.addAll(formControlsHardwareProperties(entityCreateDTO));
        formControls.addAll(formControlsLocationProperties(entityCreateDTO));

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

    public void validate(EntityCreateDTO entityCreateDTO) {
        var formModel = formModel(entityCreateDTO);
        validateFormModelUsingFormModelValidator(formModel, "ENTITY CREATE");
    }

    public void validate(EntityEditDTO entityEditDTO) {

        if (entityEditDTO.getOwner() == null || entityEditDTO.getOwner().isEmpty())
            throw new EntityFormControlErrorException("Owner id is required");

        var formModel = formModel(entityEditDTO);
        validateFormModelUsingFormModelValidator(formModel, "ENTITY EDIT");
    }

    private void validateFormModelUsingFormModelValidator(FormModel formModel, String formName) {
        if (!this.tFMV.isValid(formModel)) {
            throwEntityFormControlErrorException(this.tFMV.getError());
        }
        log.debug( formName + " FORM IS VALID");
    }
}
