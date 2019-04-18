package de.uni.stuttgart.ipvs.tm.topics.form;

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

import static de.uni.stuttgart.ipvs.form.utils.FormControlValidators.LENGTH;
import static de.uni.stuttgart.ipvs.tm.topics.form.TopicFormControlIds.*;

@Configuration
@EnableConfigurationProperties(TopicFormControlErrorMessages.class)
public class BeansTopicForm {

    private final TopicFormControlErrorMessages tfcEM;

    public BeansTopicForm(TopicFormControlErrorMessages tfcEM) {
        this.tfcEM = tfcEM;
    }

    @Bean
    @Qualifier("topicFormModel")
    FormModelValidator topicFormModelValidator() {
        return new FormModelValidatorImpl(topicFormControlValidators());
    }

    private Map<String, FormControlValidator> topicFormControlValidators() {

        var map = new HashMap<String, FormControlValidator>();

        map.put(PATH, new FormControlValidatorImpl(this.tfcEM.getPath(), LENGTH));
        map.put(DATA_TYPE, new FormControlValidatorImpl(this.tfcEM.getDataType(), LENGTH));
        map.put(MIDDLEWARE_ENDPOINT, new FormControlValidatorImpl(this.tfcEM.getMiddlewareEndpoint(), LENGTH));
        map.put(TOPIC_TYPE, new FormControlValidatorImpl(this.tfcEM.getTopicType(), LENGTH));
        map.put(PROTOCOL, new FormControlValidatorImpl(this.tfcEM.getProtocol(), LENGTH));

        map.put(UNIT, new FormControlValidatorImpl(this.tfcEM.getUnit(), LENGTH));
        map.put(HARDWARE_TYPE, new FormControlValidatorImpl(this.tfcEM.getHardwareType(), LENGTH));

        map.put(META_MODEL, new FormControlValidatorImpl(this.tfcEM.getMetaModel(), LENGTH));
        map.put(META_MODEL_TYPE, new FormControlValidatorImpl(this.tfcEM.getMetaModelType(), LENGTH));
        map.put(MESSAGE_FORMAT, new FormControlValidatorImpl(this.tfcEM.getMessageFormat(), LENGTH));

        return map;
    }
}
