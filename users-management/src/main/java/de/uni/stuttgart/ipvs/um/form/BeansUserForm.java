package de.uni.stuttgart.ipvs.um.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import de.uni.stuttgart.ipvs.form.exception.FormControlInvalidException;
import de.uni.stuttgart.ipvs.form.validation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import static de.uni.stuttgart.ipvs.um.form.UserFormControlIds.*;

@Configuration
@EnableConfigurationProperties(UserFormControlErrorMessages.class)
public class BeansUserForm {

    private final UserFormControlErrorMessages ufcEM;

    @Autowired
    public BeansUserForm(UserFormControlErrorMessages ufcEM) {
        this.ufcEM = ufcEM;
    }

    @Bean
    @Qualifier("userCreateForm")
    FormModelValidator userCreateFormValidator() {
        return new FormModelValidatorImpl(userFormControlValidators());
    }

    @Bean
    @Qualifier("userLoginForm")
    FormModelValidator userLoginFormValidator() {
        return new FormModelValidatorImpl(principalCredentialsValidators());
    }

    private Map<String, FormControlValidator> userFormControlValidators() {

        var map = new HashMap<>(principalCredentialsValidators());

        map.put(ROLE,
                new FormControlValidatorImpl(this.ufcEM.getRole(),
                        textValuesIgnoreCase(new String[]{"Manager", "Consumer"})));

        return map;
    }

    private Map<String, FormControlValidator> principalCredentialsValidators() {

        var map = new HashMap<String, FormControlValidator>();

        map.put(USERNAME,
                new FormControlValidatorImpl(this.ufcEM.getUsername(), length(6, 20)));

        map.put(PASSWORD,
                new FormControlValidatorImpl(
                        this.ufcEM.getPassword(), patternMatch(Pattern.compile("^[a-zA-Z0-9-_]+$"))));

        return map;
    }

    private static FormControlValidatorBiConsumer<String> textValuesIgnoreCase(String[] values) {
        return (text, help) -> textValuesIgnoreCase(values, text, help);
    }

    private static void textValuesIgnoreCase(String[] values, String text, String help) throws FormControlInvalidException {

        if (values == null || !textInValuesIgnoreCase(values, text))
            throw new FormControlInvalidException(help);
    }

    private static boolean textInValuesIgnoreCase(String[] values, String text) {
        return Arrays.stream(values)
                .filter(Objects::nonNull)
                .anyMatch(value -> value.equalsIgnoreCase(text));
    }

    private static FormControlValidatorBiConsumer<String> patternMatch(Pattern pattern) {
        return (text, help) -> patternMatch(pattern, text, help);
    }

    private static void patternMatch(Pattern pattern, String text, String help) throws FormControlInvalidException {
        if (pattern == null || !pattern.matcher(text).matches())
            throw new FormControlInvalidException(help);
    }

    private static FormControlValidatorBiConsumer<String> length(int lowerBound, int upperBound) {
        return (text, help) -> length(lowerBound, upperBound, text, help);
    }

    private static void length(int lowerBound, int upperBound,
                               String text, String help) throws FormControlInvalidException {
        if (text == null || text.length() < lowerBound || text.length() > upperBound)
            throw new FormControlInvalidException(help);
    }
}
