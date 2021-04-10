package com.bulkregistration.exception;

public class FileProcessingException extends RuntimeException {

    public FileProcessingException(String message, Throwable t) {
        super(message, t);
    }
}
