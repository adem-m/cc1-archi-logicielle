package com.mrizak.payment.domain;

import com.mrizak.register.domain.MemberId;

import java.time.LocalDateTime;

public final class InitialPayment extends Payment {
    private static final double INITIAL_PAYMENT_AMOUNT = 10;

    private InitialPayment(PaymentId id, MemberId memberId, Amount amount, LocalDateTime dateTime) {
        super(id, memberId, amount, dateTime);
    }

    public static InitialPayment of(PaymentId id, MemberId memberId, LocalDateTime dateTime) {
        return new InitialPayment(id, memberId, Amount.of(INITIAL_PAYMENT_AMOUNT), dateTime);
    }
}
