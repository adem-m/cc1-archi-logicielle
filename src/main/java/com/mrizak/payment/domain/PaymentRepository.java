package com.mrizak.payment.domain;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    void save(Payment payment);

    Optional<Payment> byId(PaymentId paymentId);

    PaymentId nextIdentity();

    List<Payment> findAll();
}
