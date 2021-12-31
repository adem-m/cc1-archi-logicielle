package com.mrizak.register.application;

import com.mrizak.kernel.event.EventListener;

public final class CreateMemberEventListener implements EventListener<CreateMemberEvent> {
    @Override
    public void listenTo(CreateMemberEvent event) {
        System.out.println("listening CreateMemberEvent.");
    }
}
