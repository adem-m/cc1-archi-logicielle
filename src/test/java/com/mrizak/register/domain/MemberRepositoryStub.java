package com.mrizak.register.domain;

import com.mrizak.kernel.NoSuchEntityException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemberRepositoryStub implements MemberRepository {
    private final AtomicInteger count = new AtomicInteger(1);
    private final Map<MemberId, Member> data = new ConcurrentHashMap<>();

    @Override
    public void save(Member member) {
        data.put(member.getId(), member);
    }

    @Override
    public Member byId(MemberId memberId) {
        final Member member = data.get(memberId);
        if (member == null) {
            throw NoSuchEntityException.withId(memberId);
        }
        return member;
    }

    @Override
    public MemberId nextIdentity() {
        return MemberId.of(count.getAndIncrement());
    }

    @Override
    public List<Member> findAll() {
        return List.copyOf(data.values());
    }
}
