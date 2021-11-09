package com.mrizak;

import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberRepository;
import com.mrizak.register.domain.RegistrationService;
import com.mrizak.register.infra.MemberFactory;
import com.mrizak.register.infra.PSQLMemberRepository;

public class App {
    public static void main(String[] args) {
        MemberRepository memberRepository = new PSQLMemberRepository();
        RegistrationService registrationService = new RegistrationService(memberRepository);
        Member member = MemberFactory.createStandard(
                memberRepository.nextIdentity(),
                "Adem",
                "Mrizak");
        registrationService.registerMember(member);
    }
}
