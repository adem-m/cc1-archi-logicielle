package com.mrizak.register.application;

import com.mrizak.kernel.command.Command;

public final class CreateMember implements Command {

    public final String firstname;
    public final String lastname;


    public CreateMember(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
