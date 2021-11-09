package com.mrizak.register.domain;

public final class PremiumMember extends Member {
    private PremiumMember(MemberId id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public static PremiumMember of(MemberId id, String firstName, String lastName) {
        return new PremiumMember(id, firstName, lastName);
    }
}
