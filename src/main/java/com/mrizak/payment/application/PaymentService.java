package com.mrizak.payment.application;

import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.payment.domain.validation.PaymentValidationEngine;

public final class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentValidationEngine paymentValidationEngine;

    public PaymentService(PaymentRepository paymentRepository, PaymentValidationEngine paymentValidationEngine) {
        this.paymentRepository = paymentRepository;
        this.paymentValidationEngine = paymentValidationEngine;
    }

    public void proceedPayment(Payment payment) {
        if (!paymentValidationEngine.test(payment)) {
            throw new IllegalArgumentException("Invalid payment");
        }
        this.paymentRepository.save(payment);
    }
}
