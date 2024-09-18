package org.jonas.spellify.exception;

public record ErrorResponse(
        int statusCode,
        String message,
        String timestamp
) {

}
