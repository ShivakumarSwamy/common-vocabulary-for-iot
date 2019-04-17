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
import static de.uni.stuttgart.ipvs.tm.form.HardwareTypeFormControlIds.*;

@Configuration
@EnableConfigurationProperties(HardwareTypeFormControlErrorMessages.class)
public class BeansHardwareTypeForm {

    private final HardwareTypeFormControlErrorMessages htfEM;

    @Autowired
    public BeansHardwareTypeForm(HardwareTypeFormControlErrorMessages htfEM) {
        this.htfEM = htfEM;
    }

    @Bean
    @Qualifier("hardwareTypeCreateForm")
    FormModelValidator hardwareTypeCreateFormValidator() {
        return new FormModelValidatorImpl(hardwareTypeFormControlValidators());
    }


    private Map<String, FormControlValidator> hardwareTypeFormControlValidators() {

        var map = new HashMap<String, FormControlValidator>();

        map.put(HARDWARE_COMPONENT, new FormControlValidatorImpl(this.htfEM.getHardwareComponent(), LENGTH));
        map.put(CATEGORY, new FormControlValidatorImpl(this.htfEM.getCategory(), LENGTH));
        map.put(LABEL, new FormControlValidatorImpl(this.htfEM.getLabel(), LENGTH));
        map.put(COMMENT, new FormControlValidatorImpl(this.htfEM.getComment(), LENGTH));

        return map;
    }

}
