package com.mrizak.payment.infra;

import com.mrizak.payment.domain.Amount;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.validation.InvalidMemberException;
import com.mrizak.register.domain.validation.MemberValidator;

public final class PaymentFactory {
    private static final double INITIAL_PAYMENT_AMOUNT = 10;

    public static Payment createInitialPayment(PaymentId id, Member member) {
        if (!MemberValidator.getInstance().test(member)) {
            throw new InvalidMemberException();
        }
        return Payment.of(id, member.getId(), new Amount(INITIAL_PAYMENT_AMOUNT));
    }
}
