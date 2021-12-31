package com.mrizak.register.domain;

import java.util.List;

public interface MemberRepository {
    void save(Member member);

    Member byId(MemberId memberId);

    MemberId nextIdentity();

    List<Member> findAll();
}
