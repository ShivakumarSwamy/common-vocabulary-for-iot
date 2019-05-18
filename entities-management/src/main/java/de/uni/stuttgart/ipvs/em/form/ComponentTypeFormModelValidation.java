package de.uni.stuttgart.ipvs.em.form;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

import de.uni.stuttgart.ipvs.em.dto.ComponentTypeCreateDTO;
import de.uni.stuttgart.ipvs.form.control.FormControl;
import de.uni.stuttgart.ipvs.form.control.FormControlImpl;
import de.uni.stuttgart.ipvs.form.model.FormModel;
import de.uni.stuttgart.ipvs.form.model.FormModelImpl;
import de.uni.stuttgart.ipvs.form.validation.FormControlError;
import de.uni.stuttgart.ipvs.form.validation.FormModelValidator;

import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.em.form.ComponentTypeFormControlIds.*;

@Component
@Slf4j
public class ComponentTypeFormModelValidation {


    private final FormModelValidator ctFMV;

    @Autowired
    public ComponentTypeFormModelValidation(@Qualifier("componentTypeCreateFormValidator") FormModelValidator ctFMV) {
        this.ctFMV = ctFMV;
    }

    public void validate(ComponentTypeCreateDTO componentTypeCreateDTO) {
        var formModel = formModel(componentTypeCreateDTO);

        if (!this.ctFMV.isValid(formModel)) {
            throwComponentTypeFormControlErrorException(this.ctFMV.getError());
        }
        log.debug("COMPONENT TYPE CREATE FORM IS VALID");
    }

    private void throwComponentTypeFormControlErrorException(FormControlError formControlError) {
        log.error("FORM CONTROL ERROR: " + formControlError.toString());
        throw new ComponentTypeFormControlErrorException(formControlError.getHelp());
    }

    private FormModel formModel(ComponentTypeCreateDTO componentTypeCreateDTO) {
        return new FormModelImpl(formControls(componentTypeCreateDTO));
    }

    private Collection<FormControl> formControls(ComponentTypeCreateDTO componentTypeCreateDTO) {
        var componentTypeFormControl =
                new FormControlImpl<>(COMPONENT, componentTypeCreateDTO.getComponent());

        var categoryFormControl = new FormControlImpl<>(CATEGORY, componentTypeCreateDTO.getCategory());
        var labelFormControl = new FormControlImpl<>(LABEL, componentTypeCreateDTO.getLabel());
        var commentFormControl = new FormControlImpl<>(COMMENT, componentTypeCreateDTO.getComment());

        return List.of(componentTypeFormControl, categoryFormControl, labelFormControl, commentFormControl);
    }

}
