package de.uni.stuttgart.ipvs.em.form;

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

import static de.uni.stuttgart.ipvs.em.form.ComponentTypeFormControlIds.*;
import static de.uni.stuttgart.ipvs.form.utils.FormControlValidators.LENGTH;

@Configuration
@EnableConfigurationProperties(ComponentTypeFormControlErrorMessages.class)
public class BeansComponentTypeForm {

    private final ComponentTypeFormControlErrorMessages ctfEM;

    @Autowired
    public BeansComponentTypeForm(ComponentTypeFormControlErrorMessages ctfEM) {
        this.ctfEM = ctfEM;
    }

    @Bean
    @Qualifier("componentTypeCreateFormValidator")
    FormModelValidator componentTypeCreateFormValidator() {
        return new FormModelValidatorImpl(componentTypeFormControlValidators());
    }


    private Map<String, FormControlValidator> componentTypeFormControlValidators() {

        var map = new HashMap<String, FormControlValidator>();

        map.put(COMPONENT, new FormControlValidatorImpl(this.ctfEM.getComponent(), LENGTH));
        map.put(COMPONENT_CATEGORY, new FormControlValidatorImpl(this.ctfEM.getComponentCategory(), LENGTH));
        map.put(LABEL, new FormControlValidatorImpl(this.ctfEM.getLabel(), LENGTH));
        map.put(COMMENT, new FormControlValidatorImpl(this.ctfEM.getComment(), LENGTH));

        return map;
    }

}
