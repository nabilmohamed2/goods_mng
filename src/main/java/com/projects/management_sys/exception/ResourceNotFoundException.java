package com.projects.management_sys.exception;

public class ResourceNotFoundException extends  RuntimeException{
    public ResourceNotFoundException (String message) {
        super(message);
    }
}
