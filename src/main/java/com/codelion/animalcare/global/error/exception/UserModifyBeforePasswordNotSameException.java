package com.codelion.animalcare.global.error.exception;

public class UserModifyBeforePasswordNotSameException extends RuntimeException{
    public UserModifyBeforePasswordNotSameException(String message){
        super(message);
    }
}
