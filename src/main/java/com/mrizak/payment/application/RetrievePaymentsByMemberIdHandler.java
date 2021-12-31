package com.mrizak.payment.application;

import com.mrizak.kernel.query.QueryHandler;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentRepository;

import java.util.List;

public final class RetrievePaymentsByMemberIdHandler implements QueryHandler<RetrievePaymentsByMemberId, List<Payment>> {
    private final PaymentRepository paymentRepository;

    public RetrievePaymentsByMemberIdHandler(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> handle(RetrievePaymentsByMemberId query) {
        return paymentRepository.byMemberId(query.memberId);
    }
}
