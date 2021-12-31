package com.mrizak.payment.application;

import com.mrizak.kernel.query.Query;
import com.mrizak.register.domain.MemberId;

public final class RetrievePaymentsByMemberId implements Query {
    public final MemberId memberId;

    public RetrievePaymentsByMemberId(MemberId memberId) {
        this.memberId = memberId;
    }
}
