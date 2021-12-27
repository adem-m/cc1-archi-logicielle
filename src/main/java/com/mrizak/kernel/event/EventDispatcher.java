package com.mrizak.kernel.event;

public interface EventDispatcher<E extends Event> {
    void dispatch(E event);
}
