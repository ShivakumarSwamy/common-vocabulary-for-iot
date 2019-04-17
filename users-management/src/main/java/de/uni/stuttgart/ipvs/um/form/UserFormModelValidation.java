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
import de.uni.stuttgart.ipvs.um.auth.PrincipalCredentials;
import de.uni.stuttgart.ipvs.um.auth.login.UserLoginDTO;
import de.uni.stuttgart.ipvs.um.users.dto.UserCreateDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static de.uni.stuttgart.ipvs.um.form.UserFormControlIds.*;

@Component
@Slf4j
public class UserFormModelValidation {

    private final FormModelValidator userCreateFMV;
    private final FormModelValidator userLoginFMV;

    public UserFormModelValidation(@Qualifier("userCreateForm") FormModelValidator userCreateFMV,
                                   @Qualifier("userLoginForm") FormModelValidator userLoginFMV) {
        this.userCreateFMV = userCreateFMV;
        this.userLoginFMV = userLoginFMV;
    }

    public void validate(UserCreateDTO userCreateDTO) {
        var formModel = formModel(userCreateDTO);

        if (!this.userCreateFMV.isValid(formModel)) {
            throwUserFormControlErrorException(this.userCreateFMV.getError());
        }
        log.debug("USER CREATE FORM IS VALID");
    }

    public void validate(UserLoginDTO userLoginDTO) {
        var formModel = formModel(userLoginDTO);

        if (!this.userLoginFMV.isValid(formModel)) {
            throwUserFormControlErrorException(this.userLoginFMV.getError());
        }
        log.debug("USER LOGIN FORM IS VALID");
    }

    private void throwUserFormControlErrorException(FormControlError formControlError) {
        log.error("FORM CONTROL ERROR: " + formControlError.toString());
        throw new UserFormControlErrorException(formControlError.getHelp());
    }


    private FormModel formModel(UserCreateDTO userCreateDTO) {
        return new FormModelImpl(formControls(userCreateDTO));
    }

    private FormModel formModel(UserLoginDTO userLoginDTO) {
        return new FormModelImpl(formControlsPC(userLoginDTO));
    }

    private Collection<FormControl> formControls(UserCreateDTO userCreateDTO) {

        var userCreateFormControls = new ArrayList<>(formControlsPC(userCreateDTO));
        userCreateFormControls.add(new FormControlImpl<>(ROLE, userCreateDTO.getRole()));

        return userCreateFormControls;
    }

    private Collection<FormControl> formControlsPC(PrincipalCredentials principalCredentials) {
        var usernameFormControl = new FormControlImpl<>(USERNAME, principalCredentials.getUsername());
        var passwordFormControl = new FormControlImpl<>(PASSWORD, principalCredentials.getPassword());

        return List.of(usernameFormControl, passwordFormControl);
    }


}
