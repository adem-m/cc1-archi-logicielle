package com.mrizak.payment.infra;

import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.register.domain.MemberId;

import java.util.List;

public final class PSQLPaymentRepository implements PaymentRepository {
    @Override
    public void save(Payment payment) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Payment lastPaymentOfMember(MemberId memberId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Payment byId(PaymentId paymentId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Payment> byMemberId(MemberId memberId) {
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
