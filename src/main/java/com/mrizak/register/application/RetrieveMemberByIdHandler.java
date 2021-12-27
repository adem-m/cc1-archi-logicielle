package com.mrizak.register.application;

import com.mrizak.kernel.query.QueryHandler;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberRepository;

public class RetrieveMemberByIdHandler implements QueryHandler<RetrieveMemberById, Member> {

    private final MemberRepository memberRepository;

    public RetrieveMemberByIdHandler(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member handle(RetrieveMemberById query) {
        return memberRepository.byId(query.memberId);
    }
}
