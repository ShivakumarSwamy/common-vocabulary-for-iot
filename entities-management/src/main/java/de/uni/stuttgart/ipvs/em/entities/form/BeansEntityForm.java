package de.uni.stuttgart.ipvs.em.entities.form;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.uni.stuttgart.ipvs.form.validation.FormControlValidator;
import de.uni.stuttgart.ipvs.form.validation.FormControlValidatorImpl;
import de.uni.stuttgart.ipvs.form.validation.FormModelValidator;
import de.uni.stuttgart.ipvs.form.validation.FormModelValidatorImpl;

import java.util.HashMap;
import java.util.Map;

import static de.uni.stuttgart.ipvs.em.entities.form.EntityFormControlIds.*;
import static de.uni.stuttgart.ipvs.form.utils.FormControlValidators.LENGTH;

@Configuration
@EnableConfigurationProperties(EntityFormControlErrorMessages.class)
public class BeansEntityForm {

    private final EntityFormControlErrorMessages tfcEM;

    public BeansEntityForm(EntityFormControlErrorMessages tfcEM) {
        this.tfcEM = tfcEM;
    }

    @Bean
    @Qualifier("entityFormModel")
    FormModelValidator entityFormModelValidator() {
        return new FormModelValidatorImpl(enityFormControlValidators());
    }

    private Map<String, FormControlValidator> enityFormControlValidators() {

        var map = new HashMap<String, FormControlValidator>();

        map.put(PATH, formControlValidatorHasLength(this.tfcEM.getPath()));
        map.put(DATA_TYPE, formControlValidatorHasLength(this.tfcEM.getDataType()));
        map.put(MIDDLEWARE_ENDPOINT, formControlValidatorHasLength(this.tfcEM.getMiddlewareEndpoint()));
        map.put(TOPIC_TYPE, formControlValidatorHasLength(this.tfcEM.getTopicType()));
        map.put(PROTOCOL, formControlValidatorHasLength(this.tfcEM.getProtocol()));

        map.put(UNIT, formControlValidatorHasLength(this.tfcEM.getUnit()));
        map.put(HARDWARE_TYPE, formControlValidatorHasLength(this.tfcEM.getHardwareType()));

        map.put(META_MODEL, formControlValidatorHasLength(this.tfcEM.getMetaModel()));
        map.put(META_MODEL_TYPE, formControlValidatorHasLength(this.tfcEM.getMetaModelType()));
        map.put(MESSAGE_FORMAT, formControlValidatorHasLength(this.tfcEM.getMessageFormat()));

        map.put(COUNTRY, formControlValidatorHasLength(this.tfcEM.getCountry()));
        map.put(STATE, formControlValidatorHasLength(this.tfcEM.getState()));
        map.put(CITY, formControlValidatorHasLength(this.tfcEM.getCity()));
        map.put(STREET, formControlValidatorHasLength(this.tfcEM.getStreet()));
        map.put(POINT, formControlValidatorHasLength(this.tfcEM.getPoint()));

        return map;
    }

    private static FormControlValidator formControlValidatorHasLength(String help) {
        return new FormControlValidatorImpl(help, LENGTH);
    }
}
