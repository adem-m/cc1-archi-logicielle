package com.mrizak.register.domain;

public final class StandardMember extends Member {
    private StandardMember(MemberId id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public static StandardMember of(MemberId id, String firstName, String lastName) {
        return new StandardMember(id, firstName, lastName);
    }
}
