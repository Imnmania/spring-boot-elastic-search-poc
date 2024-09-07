package me.niloybiswas.elasticsearch_poc.exception;

public class AlreadyExistsException extends  Exception {

    public AlreadyExistsException() {
        super("Item already exists");
    }

    public AlreadyExistsException(String message) {
        super(message);
    }

}
