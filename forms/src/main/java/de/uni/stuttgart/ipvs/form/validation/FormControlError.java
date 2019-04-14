package de.uni.stuttgart.ipvs.form.validation;

import lombok.Value;

@Value
public class FormControlError<T> {

    private final String id;
    private final T value;
    private final String help;

}
