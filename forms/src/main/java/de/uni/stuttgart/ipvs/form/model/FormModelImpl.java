package de.uni.stuttgart.ipvs.form.model;

import lombok.Value;

import de.uni.stuttgart.ipvs.form.control.FormControl;

import java.util.Collection;

@Value
public class FormModelImpl implements FormModel {
    private final Collection<FormControl> formControls;
}
