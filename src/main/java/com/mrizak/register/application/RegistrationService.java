package com.mrizak.register.application;

import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberRepository;

public final class RegistrationService {
    private final MemberRepository memberRepository;

    public RegistrationService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void registerMember(Member member) {
        this.memberRepository.save(member);
    }
}
