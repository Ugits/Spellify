package org.jonas.spellify.service.validation;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.jonas.spellify.exception.SpellValidationException;
import org.jonas.spellify.model.dto.SpellDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SpellValidationHandler {

    private final Validator validator;

    public SpellValidationHandler(Validator validator) {
        this.validator = validator;
    }

    public void validateDTO(SpellDTO spellDTO) {

        Set<ConstraintViolation<SpellDTO>> violations = validator.validate(spellDTO);

        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));

            throw new SpellValidationException("Validation failed: " + errorMessage);
        }
    }

}
