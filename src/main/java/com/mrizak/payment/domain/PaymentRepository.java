package com.mrizak.payment.domain;

import com.mrizak.register.domain.MemberId;

import java.util.List;

public interface PaymentRepository {
    void save(Payment payment);

    Payment lastPaymentOfMember(MemberId memberId);

    Payment byId(PaymentId paymentId);

    List<Payment> byMemberId(MemberId memberId);

    PaymentId nextIdentity();

    List<Payment> findAll();
}
