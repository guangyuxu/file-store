package com.wov.gavin.domain.exception;

public class FileExistException extends RuntimeException {
    public FileExistException(String fileName) {
        super("File " + fileName + " has been existed");
    }
}
