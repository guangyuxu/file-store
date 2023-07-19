package com.woven.gavin.domain.exception;

public class FileNotExistException extends RuntimeException {
    public FileNotExistException(String fileName) {
        super("File " + fileName + " does not exist");
    }
}
