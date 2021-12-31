package com.mrizak.register.application;

import com.mrizak.kernel.event.ApplicationEvent;
import com.mrizak.register.domain.MemberId;

public final class CreateMemberEvent implements ApplicationEvent {
    private final MemberId memberId;

    public CreateMemberEvent(MemberId memberId) {
        this.memberId = memberId;
    }
}
