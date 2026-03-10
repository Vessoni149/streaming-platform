package com.streaming.user_service.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(Long id, String entityName){
        super(String.format("Resource not found - id not found: %d - Resource's name: %s",
                id, entityName));
    }
    public ResourceNotFoundException(String entityName){
        super(String.format("Resource not found by its name - Resource's name: %s",
                entityName));
    }
}
