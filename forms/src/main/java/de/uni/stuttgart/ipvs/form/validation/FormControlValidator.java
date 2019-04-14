package de.uni.stuttgart.ipvs.form.validation;

public interface FormControlValidator {

    String getHelp();

    FormControlValidatorBiConsumer getValidatorBiConsumer();

}
