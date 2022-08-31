package com.codelion.animalcare.global.error.exception;

public class DoctorModifyBeforePasswordNotSameException extends RuntimeException{
    public DoctorModifyBeforePasswordNotSameException(String message){
        super(message);
    }
}
