package com.codelion.animalcare.domain.post.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException(String msg) {
        super(msg);
    }
}
