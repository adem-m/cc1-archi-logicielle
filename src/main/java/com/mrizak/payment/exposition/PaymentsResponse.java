package com.mrizak.payment.exposition;

import java.util.List;

public class PaymentsResponse {
    public final List<PaymentResponse> payments;

    public PaymentsResponse(List<PaymentResponse> paymentResponses) {
        this.payments = paymentResponses;
    }
}
