package org.skillbox.springboot_rest.exeption_handling;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}