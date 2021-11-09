package com.mrizak.payment.infra;

import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.payment.domain.PaymentRepository;

import java.util.List;
import java.util.Optional;

public final class PSQLPaymentRepository implements PaymentRepository {
    @Override
    public void save(Payment payment) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Optional<Payment> byId(PaymentId paymentId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public PaymentId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Payment> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
