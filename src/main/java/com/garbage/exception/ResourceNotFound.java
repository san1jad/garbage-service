package com.garbage.exception;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound() {
        super("Not Found");
    }

    public ResourceNotFound(String message) {
        super(message);
    }
}
