package me.niloybiswas.elasticsearch_poc.exception;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super("Item not found");
    }

    public NotFoundException(String message) {
        super(message);
    }

}
