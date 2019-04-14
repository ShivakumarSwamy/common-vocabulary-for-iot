package de.uni.stuttgart.ipvs.form.control;

import lombok.Value;

@Value
public class FormControlImpl<T> implements FormControl<T> {

    private final String id;

    private final T value;
}
