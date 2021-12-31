package com.mrizak.payment.domain;

public enum PaymentType {
    INITIAL("initial"),
    MONTHLY("monthly");

    private final String value;

    PaymentType(String value) {
        this.value = value;
    }

    public static PaymentType fromClass(Class<? extends Payment> aClass) {
        if (aClass == InitialPayment.class) {
            return INITIAL;
        }
        if (aClass == MonthlyPayment.class) {
            return MONTHLY;
        }
        throw new IllegalStateException();
    }

    public String getValue() {
        return value;
    }
}
