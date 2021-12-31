package com.mrizak.payment.application;

import com.mrizak.kernel.query.QueryHandler;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentRepository;

public final class RetrievePaymentByIdHandler implements QueryHandler<RetrievePaymentById, Payment> {
    private final PaymentRepository paymentRepository;

    public RetrievePaymentByIdHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment handle(RetrievePaymentById query) {
        return paymentRepository.byId(query.paymentId);
    }
}
