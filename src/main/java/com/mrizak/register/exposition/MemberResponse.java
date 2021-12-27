package com.mrizak.register.exposition;

public class MemberResponse {
    public String id;
    public String firstname;
    public String lastname;

    public MemberResponse(String id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
