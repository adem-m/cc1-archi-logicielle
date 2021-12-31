package com.mrizak.payment.domain;

import com.mrizak.register.domain.MemberId;

import java.time.LocalDateTime;

public abstract class Payment {
    private final PaymentId id;
    private final MemberId memberId;
    private final Amount amount;
    private final LocalDateTime dateTime;

    protected Payment(PaymentId id, MemberId memberId, Amount amount, LocalDateTime dateTime) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
