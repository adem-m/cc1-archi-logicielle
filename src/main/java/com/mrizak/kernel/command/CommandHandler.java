package com.mrizak.kernel.command;

@FunctionalInterface
public interface CommandHandler<C extends Command, R> {
    R handle(C command);
}
