package com.mrizak.payment.domain;

public final class Amount {
    private final double value;

    public Amount(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
