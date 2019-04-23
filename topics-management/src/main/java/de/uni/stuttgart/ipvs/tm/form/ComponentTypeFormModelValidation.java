package de.uni.stuttgart.ipvs.tm.form;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

import de.uni.stuttgart.ipvs.form.control.FormControl;
import de.uni.stuttgart.ipvs.form.control.FormControlImpl;
import de.uni.stuttgart.ipvs.form.model.FormModel;
import de.uni.stuttgart.ipvs.form.model.FormModelImpl;
import de.uni.stuttgart.ipvs.form.validation.FormControlError;
import de.uni.stuttgart.ipvs.form.validation.FormModelValidator;
import de.uni.stuttgart.ipvs.tm.dto.HardwareTypeCreateDTO;

import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.tm.form.HardwareTypeFormControlIds.*;

@Component
@Slf4j
public class HardwareTypeFormModelValidation {


    private final FormModelValidator htFMV;

    @Autowired
    public HardwareTypeFormModelValidation(@Qualifier("hardwareTypeCreateForm") FormModelValidator htFMV) {
        this.htFMV = htFMV;
    }

    public void validate(HardwareTypeCreateDTO hardwareTypeCreateDTO) {
        var formModel = formModel(hardwareTypeCreateDTO);

        if (!this.htFMV.isValid(formModel)) {
            throwHardwareTypeFormControlErrorException(this.htFMV.getError());
        }
        log.debug("HARDWARE TYPE CREATE FORM IS VALID");
    }

    private void throwHardwareTypeFormControlErrorException(FormControlError formControlError) {
        log.error("FORM CONTROL ERROR: " + formControlError.toString());
        throw new HardwareTypeFormControlErrorException(formControlError.getHelp());
    }

    private FormModel formModel(HardwareTypeCreateDTO hardwareTypeCreateDTO) {
        return new FormModelImpl(formControls(hardwareTypeCreateDTO));
    }

    private Collection<FormControl> formControls(HardwareTypeCreateDTO hardwareTypeCreateDTO) {
        var hardwareTypeFormControl =
                new FormControlImpl<>(HARDWARE_COMPONENT, hardwareTypeCreateDTO.getHardwareComponent());

        var categoryFormControl = new FormControlImpl<>(CATEGORY, hardwareTypeCreateDTO.getCategory());
        var labelFormControl = new FormControlImpl<>(LABEL, hardwareTypeCreateDTO.getLabel());
        var commentFormControl = new FormControlImpl<>(COMMENT, hardwareTypeCreateDTO.getComment());

        return List.of(hardwareTypeFormControl, categoryFormControl, labelFormControl, commentFormControl);
    }

}
