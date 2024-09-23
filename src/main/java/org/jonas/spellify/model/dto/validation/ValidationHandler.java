package org.jonas.spellify.model.dto.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.jonas.spellify.exception.SpellValidationException;
import org.jonas.spellify.model.dto.SpellDTO;
import org.jonas.spellify.model.dto.SpellIdDTO;
import org.jonas.spellify.model.dto.UpdateSpellDTO;
import org.springframework.stereotype.Component;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ValidationHandler {

    private final Validator validator;

    public ValidationHandler(Validator validator) {
        this.validator = validator;
    }

    public void validateSpellDTO(SpellDTO spellDTO) {

        Set<ConstraintViolation<SpellDTO>> violations = validator.validate(spellDTO);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));

            throw new SpellValidationException("Validation failed: " + errorMessage + " <-- [index: " + spellDTO.index()+"]");
        }
    }

    public void validateUpdateSpellDTO(UpdateSpellDTO updateSpellDTO) {

        Set<ConstraintViolation<UpdateSpellDTO>> violations = validator.validate(updateSpellDTO);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));

            throw new SpellValidationException("Validation failed: " + errorMessage);
        }
    }

    public void validateSpellIdDTO(SpellIdDTO spellIdDTO) {

        Set<ConstraintViolation<SpellIdDTO>> violations = validator.validate(spellIdDTO);
        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));

            throw new SpellValidationException("Invalid ID: " + "[" + spellIdDTO.id() + "] : " + errorMessage);
        }

    }

}
