package de.uni.stuttgart.ipvs.form.utils;

import de.uni.stuttgart.ipvs.form.validation.FormControlValidatorBiConsumer;

public class FormControlValidators {

    public static final FormControlValidatorBiConsumer<String> LENGTH;

    static {
        LENGTH = Validators::hasLength;
    }
}
