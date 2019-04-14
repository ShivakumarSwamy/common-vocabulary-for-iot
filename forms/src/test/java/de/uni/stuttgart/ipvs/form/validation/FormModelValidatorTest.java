package de.uni.stuttgart.ipvs.form.validation;

import org.junit.jupiter.api.Test;

import de.uni.stuttgart.ipvs.form.control.FormControlImpl;
import de.uni.stuttgart.ipvs.form.model.FormModel;
import de.uni.stuttgart.ipvs.form.model.FormModelImpl;

import java.util.List;
import java.util.Map;

import static de.uni.stuttgart.ipvs.form.utils.FormControlValidators.LENGTH;
import static org.junit.jupiter.api.Assertions.*;

class FormModelValidatorTest {

    private static final String USERNAME_ID = "username";

    @Test
    void validateSingleUsernameFormModelExpectFormControlErrorObjectPresent() {

        FormModelValidator validator = userFormModelValidator();

        FormModel model = new FormModelImpl(List.of(new FormControlImpl<>(USERNAME_ID, "")));
        assertFalse(validator.isValid(model));

        FormControlError error = validator.getError();
        assertNotNull(error);
        assertEquals("username", error.getId());
        assertEquals("Username should have length", error.getHelp());
        assertEquals("", error.getValue());
    }

    @Test
    void validateSingleUsernameFormModelExpectFormControlErrorObjectAbsent() {

        FormModelValidator validator = userFormModelValidator();

        FormModel model = new FormModelImpl(List.of(new FormControlImpl<>(USERNAME_ID, "foo")));
        assertTrue(validator.isValid(model));

        FormControlError error = validator.getError();
        assertNull(error);
    }

    private static FormModelValidator userFormModelValidator() {
        return new FormModelValidatorImpl(
                Map.of(USERNAME_ID, new FormControlValidatorImpl("Username should have length", LENGTH)));

    }
}