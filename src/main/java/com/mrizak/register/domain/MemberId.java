package com.mrizak.register.domain;

import com.mrizak.kernel.ValueObjectID;

public final class MemberId implements ValueObjectID {
    private final int value;

    private MemberId(int value) {
        this.value = value;
    }

    public static MemberId of(int value) {
        return new MemberId(value);
    }

    public String getValue() {
        return String.valueOf(value);
    }
}
