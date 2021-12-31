package com.mrizak.payment.infra;

import com.mrizak.kernel.NoSuchEntityException;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.register.domain.MemberId;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPaymentRepository implements PaymentRepository {
    private final AtomicInteger count = new AtomicInteger(1);
    private final Map<PaymentId, Payment> payments = new ConcurrentHashMap<>();
    private final Map<MemberId, List<Payment>> paymentsByMember = new ConcurrentHashMap<>();

    @Override
    public void save(Payment payment) {
        payments.put(payment.getId(), payment);
        final MemberId memberId = payment.getMemberId();
        if (paymentsByMember.get(memberId) == null) {
            paymentsByMember.put(memberId, Collections.singletonList(payment));
        } else {
            paymentsByMember.get(memberId).add(payment);
        }
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
