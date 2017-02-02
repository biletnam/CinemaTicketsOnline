package com.netcracker.cinema.validation;

import org.apache.commons.validator.ValidatorResults;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 20.01.2017.
 */
public class ValidationExecutor {

    private List<Validator> validators;

    public ValidationExecutor() {
        validators = new ArrayList<>();
    }

    public List<Validator> getValidators() {
        return validators;
    }

    public ValidationExecutor addValidator(Validator validator) {
        this.validators.add(validator);
        return this;
    }

    public ValidationResult execute() {

        for (Validator validator : validators) {
            if(!validator.validate()) {
                return new ValidationResult(false, validator.getMessage());
            }
        }

        return new ValidationResult(true);

    }
}
