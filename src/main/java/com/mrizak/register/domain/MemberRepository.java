package com.mrizak.register.domain;

import java.util.List;

public interface MemberRepository {
    void save(Member user);

    Member byId(MemberId userId);

    MemberId nextIdentity();

    List<Member> findAll();
}
