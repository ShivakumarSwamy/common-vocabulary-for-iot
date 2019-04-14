package de.uni.stuttgart.ipvs.form.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import de.uni.stuttgart.ipvs.form.control.FormControl;
import de.uni.stuttgart.ipvs.form.exception.FormControlInvalidException;
import de.uni.stuttgart.ipvs.form.model.FormModel;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public class FormModelValidatorImpl implements FormModelValidator {

    private final Map<String, FormControlValidator> formControlValidatorsMap;

    private FormControlError error;

    @Override
    public boolean isValid(FormModel formModel) {

        var formControls = formModel.getFormControls();

        for (FormControl formControl : formControls) {
            try {
                this.validateFormControl(formControl);
            } catch (FormControlInvalidException failedControl) {
                this.error = new FormControlError<>(
                        formControl.getId(), formControl.getValue(), failedControl.getMessage());
                return false;
            }
        }
        return true;
    }

    private void validateFormControl(FormControl formControl) throws FormControlInvalidException {
        var formControlValidator = this.formControlValidatorsMap.get(formControl.getId());
        formControlValidator.getValidatorBiConsumer().accept(formControl.getValue(), formControlValidator.getHelp());
    }

}
