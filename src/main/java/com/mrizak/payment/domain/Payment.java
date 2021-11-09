package com.mrizak.payment.domain;

import com.mrizak.register.domain.Member;

public final class Payment {
    private final PaymentId id;
    private final Member member;
    private final Amount amount;

    private Payment(PaymentId id, Member member, Amount amount) {
        this.id = id;
        this.member = member;
        this.amount = amount;
    }

    public static Payment of(PaymentId id, Member member, Amount amount) {
        return new Payment(id, member, amount);
    }

    public PaymentId getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Amount getAmount() {
        return amount;
    }
}
