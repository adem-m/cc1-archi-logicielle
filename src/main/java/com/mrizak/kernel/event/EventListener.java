package com.mrizak.kernel.event;

public interface EventListener<E extends Event> {
    void listenTo(E event);
}
