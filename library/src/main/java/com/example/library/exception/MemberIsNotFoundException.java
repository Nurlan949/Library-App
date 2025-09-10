package com.example.library.exception;

public class MemberIsNotFoundException extends RuntimeException {
    public MemberIsNotFoundException(String message) {
        super(message);
    }

}
