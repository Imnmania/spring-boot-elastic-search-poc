package me.niloybiswas.elasticsearch_poc.exception;

public class ElasticSearchSSLException extends Exception {

    public ElasticSearchSSLException() {
        super("ElasticSearch SSL exception");
    }

    public ElasticSearchSSLException(String message) {
        super(message);
    }

}
