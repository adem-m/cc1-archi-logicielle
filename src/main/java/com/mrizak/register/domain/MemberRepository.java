package com.mrizak.register.domain;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    void save(Member member);

    Optional<Member> byId(MemberId memberId);

    MemberId nextIdentity();

    List<Member> findAll();
}
