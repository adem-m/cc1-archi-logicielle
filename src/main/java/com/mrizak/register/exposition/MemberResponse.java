package com.mrizak.register.exposition;

public final class MemberResponse {
    public String id;
    public String firstname;
    public String lastname;
    public String type;

    public MemberResponse(String id, String firstname, String lastname, String type) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
    }

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
