package com.mrizak.register.application;

import com.mrizak.kernel.command.Command;

public final class CreateMember implements Command {

    public final String type;
    public final String firstname;
    public final String lastname;


    public CreateMember(String type, String firstname, String lastname) {
        this.type = type;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
