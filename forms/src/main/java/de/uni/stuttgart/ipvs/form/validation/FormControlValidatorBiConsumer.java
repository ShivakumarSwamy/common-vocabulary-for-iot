package de.uni.stuttgart.ipvs.form.validation;

import de.uni.stuttgart.ipvs.form.exception.FormControlInvalidException;

public interface FormControlValidatorBiConsumer<T> {

    void accept(T value, String help) throws FormControlInvalidException;
}
