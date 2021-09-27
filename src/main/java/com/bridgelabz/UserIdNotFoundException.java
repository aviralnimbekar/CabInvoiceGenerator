package com.bridgelabz;

public class UserIdNotFoundException extends  RuntimeException {
    enum ExceptionType {NOT_FOUND}

    ExceptionType type;

    public UserIdNotFoundException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
