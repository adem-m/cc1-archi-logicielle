package com.mrizak.payment.domain;

import com.mrizak.payment.domain.validation.AmountValidator;
import com.mrizak.payment.domain.validation.InvalidAmountException;

public final class Amount {
    private final double value;

    private Amount(double value) {
        this.value = value;
    }

    public static Amount of(double value) {
        final Amount amount = new Amount(value);
        if (AmountValidator.getInstance().test(amount)) {
            return amount;
        }
        throw new InvalidAmountException(amount.getValue());
    }

    public double getValue() {
        return value;
    }
}
