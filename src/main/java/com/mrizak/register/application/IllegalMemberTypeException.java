package com.mrizak.register.application;

public final class IllegalMemberTypeException extends IllegalArgumentException {
    public IllegalMemberTypeException(String memberType) {
        super(String.format("Member type %s is invalid.", memberType));
    }
}
