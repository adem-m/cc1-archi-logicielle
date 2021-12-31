package com.mrizak.register.application;

import com.mrizak.kernel.query.QueryHandler;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberRepository;

import java.util.List;

public final class RetrieveMembersHandler implements QueryHandler<RetrieveMembers, List<Member>> {

    private final MemberRepository memberRepository;

    public RetrieveMembersHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> handle(RetrieveMembers query) {
        return memberRepository.findAll();
    }
}
