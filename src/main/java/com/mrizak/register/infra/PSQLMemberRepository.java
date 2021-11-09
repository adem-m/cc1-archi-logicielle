package com.mrizak.register.infra;

import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberId;
import com.mrizak.register.domain.MemberRepository;

import java.util.List;

public final class PSQLMemberRepository implements MemberRepository {
    @Override
    public void save(Member user) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public Member byId(MemberId userId) {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public MemberId nextIdentity() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }

    @Override
    public List<Member> findAll() {
        throw new UnsupportedOperationException("Not yet implemented.");
    }
}
