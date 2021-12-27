package com.mrizak.kernel.query;

public interface QueryBus {
    <Q extends Query, R> R send(Q query);
}
