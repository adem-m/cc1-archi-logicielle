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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentId paymentId = (PaymentId) o;

        return value == paymentId.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
