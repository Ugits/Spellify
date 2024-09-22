package org.jonas.spellify.exception;

public class SpellAlreadyExists extends RuntimeException {
    public SpellAlreadyExists(String message) {
        super(message);
    }
}
