package com.mrizak.payment.application;

public class IllegalPaymentDateException extends UnsupportedOperationException {
    public IllegalPaymentDateException() {
        super("Payment already done this month.");
    }
}
