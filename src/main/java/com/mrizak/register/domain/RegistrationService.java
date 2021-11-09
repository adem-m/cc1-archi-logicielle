package com.mrizak.register.domain;

public final class RegistrationService {
    private final MemberRepository memberRepository;

    public RegistrationService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void registerMember(Member member) {
        this.memberRepository.save(member);
    }
}
