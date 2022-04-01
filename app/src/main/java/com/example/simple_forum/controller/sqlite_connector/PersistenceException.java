package com.example.simple_forum.controller.sqlite_connector;


public class PersistenceException extends RuntimeException {
    public PersistenceException(final Exception cause) {
        super(cause);
    }
}
