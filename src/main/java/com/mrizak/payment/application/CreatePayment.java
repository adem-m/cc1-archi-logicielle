package com.mrizak.payment.application;

import com.mrizak.kernel.command.Command;

public final class CreatePayment implements Command {
    public final String memberId;

    public CreatePayment(String memberId) {
        this.memberId = memberId;
    }
}
