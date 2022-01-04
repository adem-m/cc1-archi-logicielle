package com.mrizak.payment.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public final class PaymentRequest {
    @NotNull
    @NotBlank
    public String memberId;
}
