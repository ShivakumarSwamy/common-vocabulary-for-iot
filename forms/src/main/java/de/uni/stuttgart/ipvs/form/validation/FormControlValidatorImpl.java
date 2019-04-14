package de.uni.stuttgart.ipvs.form.validation;

import lombok.Value;

@Value
public class FormControlValidatorImpl implements FormControlValidator {

    private final String help;

    private final FormControlValidatorBiConsumer validatorBiConsumer;

}
