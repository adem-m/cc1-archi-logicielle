package com.mrizak.payment.domain.validation;

import com.mrizak.kernel.NoSuchEntityException;
import com.mrizak.payment.domain.Payment;
import com.mrizak.register.domain.MemberRepository;

import java.util.function.Predicate;

public final class PaymentValidationEngine implements Predicate<Payment> {
    private final MemberRepository memberRepository;

    public PaymentValidationEngine(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public boolean test(Payment payment) {
        try {
            memberRepository.byId(payment.getMember().getId());
            return true;
        } catch (NoSuchEntityException e) {
            return false;
        }
    }
}
