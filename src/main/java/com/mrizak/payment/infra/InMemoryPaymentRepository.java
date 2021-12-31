package com.mrizak.payment.infra;

import com.mrizak.kernel.NoSuchEntityException;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.payment.domain.PaymentRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPaymentRepository implements PaymentRepository {
    private final AtomicInteger count = new AtomicInteger(0);
    private final Map<PaymentId, Payment> data = new ConcurrentHashMap<>();

    @Override
    public void save(Payment payment) {
        data.put(payment.getId(), payment);
    }

    @Override
    public Payment byId(PaymentId paymentId) {
        Payment Payment = data.get(paymentId);
        if (Payment == null) {
            throw NoSuchEntityException.withId(paymentId);
        }
        return Payment;
    }

    @Override
    public PaymentId nextIdentity() {
        return new PaymentId(count.getAndIncrement());
    }

    @Override
    public List<Payment> findAll() {
        return List.copyOf(data.values());
    }
}
