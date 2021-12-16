package com.mrizak.payment.domain;

import com.mrizak.kernel.ValueObjectID;

public final class PaymentId implements ValueObjectID {
    private final int value;

    private PaymentId(int value) {
        this.value = value;
    }

    public static PaymentId of(int value) {
        return new PaymentId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
