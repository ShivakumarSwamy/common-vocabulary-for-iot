package de.uni.stuttgart.ipvs.um.form;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Component;

import de.uni.stuttgart.ipvs.form.control.FormControl;
import de.uni.stuttgart.ipvs.form.control.FormControlImpl;
import de.uni.stuttgart.ipvs.form.model.FormModel;
import de.uni.stuttgart.ipvs.form.model.FormModelImpl;
import de.uni.stuttgart.ipvs.form.validation.FormControlError;
import de.uni.stuttgart.ipvs.form.validation.FormModelValidator;
import de.uni.stuttgart.ipvs.um.users.dto.UserCreateDTO;

import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.um.form.UserFormControlIds.*;

@Component
@Slf4j
public class UserFormModelValidation {

    private final FormModelValidator userCreateFMV;

    public UserFormModelValidation(@Qualifier("userCreateForm") FormModelValidator userCreateFMV) {
        this.userCreateFMV = userCreateFMV;
    }

    public void validate(UserCreateDTO userCreateDTO) {
        var formModel = formModel(userCreateDTO);

        if (!this.userCreateFMV.isValid(formModel)) {
            throwUserFormControlErrorException(this.userCreateFMV.getError());
        }
        log.debug("USER CREATE FORM IS VALID");
    }

    private void throwUserFormControlErrorException(FormControlError formControlError) {
        log.error("FORM CONTROL ERROR: " + formControlError.toString());
        throw new UserFormControlErrorException(formControlError.getHelp());
    }


    private FormModel formModel(UserCreateDTO userCreateDTO) {
        return new FormModelImpl(formControls(userCreateDTO));
    }

    private Collection<FormControl> formControls(UserCreateDTO userCreateDTO) {

        var usernameFormControl = new FormControlImpl<>(USERNAME, userCreateDTO.getUsername());
        var passwordFormControl = new FormControlImpl<>(PASSWORD, userCreateDTO.getPassword());
        var roleFormControl = new FormControlImpl<>(ROLE, userCreateDTO.getRole());

        return List.of(usernameFormControl, passwordFormControl, roleFormControl);
    }

}
