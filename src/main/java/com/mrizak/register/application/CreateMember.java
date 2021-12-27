package com.mrizak.register.application;

import com.mrizak.kernel.command.Command;

public final class CreateMember implements Command {

    public final String lastname;
    public final String firstname;

    public CreateMember(String lastname, String firstname) {
        this.lastname = lastname;
        this.firstname = firstname;
    }
}
