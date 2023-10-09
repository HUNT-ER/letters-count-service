package org.boldyrev.letterscount.util.validators;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class WordRequestValidator extends CustomValidator {

    protected WordRequestValidator() {
        super("Word request");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(WordRequestValidator.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(getErrors(errors));
        }
    }
}
