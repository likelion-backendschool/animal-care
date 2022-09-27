package com.codelion.animalcare.global.error.exception;

public class UserModifyAfterPasswordNotSameException extends RuntimeException{
    public UserModifyAfterPasswordNotSameException(String message){
        super(message);
    }
}
