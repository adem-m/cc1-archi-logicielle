package com.mrizak.payment.domain.validation;

import com.mrizak.payment.domain.Amount;

import java.util.function.Predicate;

public final class AmountValidator implements Predicate<Amount> {
    private static final double MINIMUM_AMOUNT = 0;
    private static final double MAXIMUM_AMOUNT = 100_000;

    private static final AmountValidator INSTANCE = new AmountValidator();

    private AmountValidator() {
    }

    public static AmountValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(Amount amount) {
        return amount.getValue() > MINIMUM_AMOUNT && amount.getValue() < MAXIMUM_AMOUNT;
    }
}
