package com.mrizak.register.domain;

public abstract class Member {
    protected final MemberId id;
    protected final String firstName;
    protected final String lastName;

    protected Member(MemberId id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public MemberId getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
