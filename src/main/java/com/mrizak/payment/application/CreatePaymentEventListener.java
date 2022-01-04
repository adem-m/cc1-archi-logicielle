package com.mrizak.payment.application;

import com.mrizak.kernel.event.EventListener;

public final class CreatePaymentEventListener implements EventListener<CreatePaymentEvent> {
    @Override
    public void listenTo(CreatePaymentEvent event) {
        System.out.println("listening CreatePaymentEvent.");
    }
}
