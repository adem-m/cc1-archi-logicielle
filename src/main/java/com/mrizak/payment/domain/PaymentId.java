package com.mrizak.payment.domain;

import com.mrizak.kernel.ValueObjectID;

public final class PaymentId implements ValueObjectID {
    private final int value;

    public PaymentId(int value) {
        this.value = value;
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
