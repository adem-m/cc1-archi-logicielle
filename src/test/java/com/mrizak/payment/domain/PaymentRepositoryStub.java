package com.mrizak.payment.domain;

import com.mrizak.kernel.NoSuchEntityException;
import com.mrizak.register.domain.MemberId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PaymentRepositoryStub implements PaymentRepository {
    private final AtomicInteger count = new AtomicInteger(1);
    private final Map<PaymentId, Payment> payments = new ConcurrentHashMap<>();
    private final Map<MemberId, List<Payment>> paymentsByMember = new ConcurrentHashMap<>();

    @Override
    public void save(Payment payment) {
        payments.put(payment.getId(), payment);
        final MemberId memberId = payment.getMemberId();
        if (paymentsByMember.get(memberId) == null) {
            paymentsByMember.put(memberId, new ArrayList<>());
            paymentsByMember.get(memberId).add(payment);
        } else {
            paymentsByMember.get(memberId).add(payment);
        }
    }

    @Override
    public Payment lastPaymentOfMember(MemberId memberId) {
        List<Payment> payments = paymentsByMember.get(memberId);
        if (payments == null || payments.isEmpty()) {
            throw new UnsupportedOperationException(String.format("Member with id %s has no payments yet.", memberId.getValue()));
        }
        return payments.stream().reduce(
                (payment, payment2) ->
                        payment.getDateTime().isAfter(payment2.getDateTime()) ? payment : payment2).get();
    }

    @Override
    public Payment byId(PaymentId paymentId) {
        final Payment payment = payments.get(paymentId);
        if (payment == null) {
            throw NoSuchEntityException.withId(paymentId);
        }
        return payment;
    }

    @Override
    public List<Payment> byMemberId(MemberId memberId) {
        if (paymentsByMember.get(memberId) == null) {
            return Collections.emptyList();
        } else {
            return paymentsByMember.get(memberId);
        }
    }

    @Override
    public PaymentId nextIdentity() {
        return new PaymentId(count.getAndIncrement());
    }

    @Override
    public List<Payment> findAll() {
        return List.copyOf(payments.values());
    }
}
