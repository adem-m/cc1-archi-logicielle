package com.mrizak.register.domain;

import com.mrizak.register.application.IllegalMemberTypeException;

public enum MemberType {
    STANDARD("standard"),
    PREMIUM("premium");

    private final String value;

    MemberType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MemberType fromString(String value) throws IllegalMemberTypeException {
        return switch (value) {
            case "standard" -> STANDARD;
            case "premium" -> PREMIUM;
            default -> throw new IllegalMemberTypeException(value);
        };
    }

    public static MemberType fromClass(Class<? extends Member> aClass) {
        if (aClass == StandardMember.class) {
            return STANDARD;
        }
        if (aClass == PremiumMember.class) {
            return PREMIUM;
        }
        throw new IllegalStateException();
    }
}
