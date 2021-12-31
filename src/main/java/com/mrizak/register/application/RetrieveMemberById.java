package com.mrizak.register.application;

import com.mrizak.kernel.query.Query;
import com.mrizak.register.domain.MemberId;

public final class RetrieveMemberById implements Query {
    public final MemberId memberId;

    public RetrieveMemberById(MemberId memberId) {
        this.memberId = memberId;
    }
}
