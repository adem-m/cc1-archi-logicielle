package com.mrizak.payment.domain.validation;

public final class InvalidAmountException extends IllegalStateException {
    public InvalidAmountException(double amount) {
        super("Invalid amount " + amount);
    }
}
