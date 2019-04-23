package de.uni.stuttgart.ipvs.tm.form;

import org.springframework.beans.factory.annotation.Autowired;
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
import static de.uni.stuttgart.ipvs.tm.form.ComponentTypeFormControlIds.*;

@Configuration
@EnableConfigurationProperties(ComponentTypeFormControlErrorMessages.class)
public class BeansComponentTypeForm {

    private final ComponentTypeFormControlErrorMessages htfEM;

    @Autowired
    public BeansComponentTypeForm(ComponentTypeFormControlErrorMessages htfEM) {
        this.htfEM = htfEM;
    }

    @Bean
    @Qualifier("componentTypeCreateFormValidator")
    FormModelValidator componentTypeCreateFormValidator() {
        return new FormModelValidatorImpl(componentTypeFormControlValidators());
    }


    private Map<String, FormControlValidator> componentTypeFormControlValidators() {

        var map = new HashMap<String, FormControlValidator>();

        map.put(COMPONENT, new FormControlValidatorImpl(this.htfEM.getComponent(), LENGTH));
        map.put(CATEGORY, new FormControlValidatorImpl(this.htfEM.getCategory(), LENGTH));
        map.put(LABEL, new FormControlValidatorImpl(this.htfEM.getLabel(), LENGTH));
        map.put(COMMENT, new FormControlValidatorImpl(this.htfEM.getComment(), LENGTH));

        return map;
    }

}
