package com.mrizak.payment.application;

import com.mrizak.kernel.Clock;
import com.mrizak.payment.domain.InitialPayment;
import com.mrizak.payment.domain.MonthlyPayment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.register.domain.Member;

public final class PaymentFactory {

    private final Clock clock;

    public PaymentFactory(Clock clock) {
        this.clock = clock;
    }

    public InitialPayment createInitialPayment(PaymentId id, Member member) {
        return InitialPayment.of(id, member.getId(), clock.now());
    }

    public MonthlyPayment createMonthlyPayment(PaymentId id, Member member) {
        return MonthlyPayment.of(id, member, clock.now());
    }
}
