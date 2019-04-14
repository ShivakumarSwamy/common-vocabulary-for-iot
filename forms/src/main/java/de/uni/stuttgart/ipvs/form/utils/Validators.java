package de.uni.stuttgart.ipvs.form.utils;

import de.uni.stuttgart.ipvs.form.exception.FormControlInvalidException;

public class Validators {

    static void hasLength(String text, String help) throws FormControlInvalidException {
        if (text == null || text.isEmpty()) throw new FormControlInvalidException(help);
    }

}
