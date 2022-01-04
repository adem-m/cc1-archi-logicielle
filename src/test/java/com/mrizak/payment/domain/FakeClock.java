package com.mrizak.payment.domain;

import com.mrizak.kernel.Clock;

import java.time.LocalDateTime;

public class FakeClock implements Clock {
    @Override
    public LocalDateTime now() {
        return LocalDateTime.of(2022, 1, 1, 12, 0);
    }
}
