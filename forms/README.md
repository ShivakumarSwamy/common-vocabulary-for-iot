# Forms Module

NOTE: Inspired by Angular Reactive Forms Module

Module to validate Form Model using Form Model Validator

Form Model represents a Data Transfer Object (DTO) received from a form from the frontend

Form Model Validator verify's the Form Model using a `FormControlValidatorBiConsumer` Bi-Consumer 

Form Control represents a form field in Form Model

## Example usage:

Validate a username form control, where the username must have length
 
```
FormModelValidator validator = new FormModelValidatorImpl(
                Map.of("username", new FormControlValidatorImpl("Username should have length", LENGTH)));

FormModel model = new FormModelImpl(List.of(new FormControlImpl<>("username", "")));
System.out.println(validator.isValid(model)); // false

System.out.println(validator.getError(model).toString()); // FormControlError(id=username, value=, help=Username should have length)
``` 

### Version 0.1.0

- interfaces:
    - FormModelValidator
    - FormModelControl
    - FormModel
    - FormControlValidatorBiConsumer
    
- FormControlError