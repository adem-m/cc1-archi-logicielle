package com.mrizak.payment.domain;

import com.mrizak.register.domain.MemberId;

public final class Payment {
    private final PaymentId id;
    private final MemberId memberId;
    private final Amount amount;

    private Payment(PaymentId id, MemberId memberId, Amount amount) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
    }

    public static Payment of(PaymentId id, MemberId memberId, Amount amount) {
        return new Payment(id, memberId, amount);
    }

    public PaymentId getId() {
        return id;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public Amount getAmount() {
        return amount;
    }
}
