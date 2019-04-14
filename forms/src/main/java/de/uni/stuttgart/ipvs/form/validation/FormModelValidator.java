package de.uni.stuttgart.ipvs.form.validation;

import de.uni.stuttgart.ipvs.form.model.FormModel;

import java.util.Map;

public interface FormModelValidator {

    boolean isValid(FormModel formModel);

    FormControlError getError();

    Map<String, FormControlValidator> getFormControlValidatorsMap();

}
