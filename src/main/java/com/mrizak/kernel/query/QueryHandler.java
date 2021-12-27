package com.mrizak.kernel.query;

@FunctionalInterface
public interface QueryHandler<Q extends Query, R> {
    R handle(Q query);
}
