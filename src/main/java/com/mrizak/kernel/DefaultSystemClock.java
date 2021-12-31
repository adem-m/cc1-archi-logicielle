package com.mrizak.kernel;

import java.time.LocalDateTime;

public final class DefaultSystemClock implements Clock {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
