package com.mrizak.payment.application;

import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.payment.domain.validation.PaymentValidationEngine;
import com.mrizak.register.domain.MemberRepository;

public final class PaymentService {
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          MemberRepository memberRepository) {
        this.paymentRepository = paymentRepository;
        this.memberRepository = memberRepository;
    }

    public void proceedInitialPayment(Payment payment) {
        PaymentValidationEngine paymentValidationEngine = new PaymentValidationEngine(memberRepository);
        if (!paymentValidationEngine.test(payment)) {
            throw new IllegalArgumentException("Invalid payment");
        }
        this.paymentRepository.save(payment);
    }
}
