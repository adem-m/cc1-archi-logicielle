package com.mrizak.payment.domain;

import com.mrizak.register.application.IllegalMemberTypeException;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberId;
import com.mrizak.register.domain.PremiumMember;
import com.mrizak.register.domain.StandardMember;

import java.time.LocalDateTime;

public final class MonthlyPayment extends Payment {
    private static final double MONTHLY_PAYMENT_AMOUNT_STANDARD_MEMBER = 20;
    private static final double MONTHLY_PAYMENT_AMOUNT_PREMIUM_MEMBER = 30;

    private MonthlyPayment(PaymentId id, MemberId memberId, Amount amount, LocalDateTime dateTime) {
        super(id, memberId, amount, dateTime);
    }

    public static MonthlyPayment of(PaymentId id, Member member, LocalDateTime dateTime) {
        if (member instanceof StandardMember) {
            return new MonthlyPayment(id, member.getId(), Amount.of(MONTHLY_PAYMENT_AMOUNT_STANDARD_MEMBER), dateTime);
        }
        if (member instanceof PremiumMember) {
            return new MonthlyPayment(id, member.getId(), Amount.of(MONTHLY_PAYMENT_AMOUNT_PREMIUM_MEMBER), dateTime);
        }
        throw new IllegalMemberTypeException();
    }
}
