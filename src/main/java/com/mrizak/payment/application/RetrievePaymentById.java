package com.mrizak.payment.application;

import com.mrizak.kernel.query.Query;
import com.mrizak.payment.domain.PaymentId;

public final class RetrievePaymentById implements Query {
    public final PaymentId paymentId;

    public RetrievePaymentById(PaymentId paymentId) {
        this.paymentId = paymentId;
    }
}
