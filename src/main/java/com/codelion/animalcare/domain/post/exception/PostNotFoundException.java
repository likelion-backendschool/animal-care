package com.codelion.animalcare.domain.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(String msg) {
        super(msg);
    }
}
