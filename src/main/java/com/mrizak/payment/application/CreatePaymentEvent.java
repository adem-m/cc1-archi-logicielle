package com.mrizak.payment.application;

import com.mrizak.kernel.event.ApplicationEvent;
import com.mrizak.payment.domain.PaymentId;

public final class CreatePaymentEvent implements ApplicationEvent {
    public final PaymentId paymentId;

    public CreatePaymentEvent(PaymentId paymentId) {
        this.paymentId = paymentId;
    }
}
