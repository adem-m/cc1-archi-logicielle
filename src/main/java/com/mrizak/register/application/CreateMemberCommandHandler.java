package com.mrizak.register.application;

import com.mrizak.kernel.command.CommandHandler;
import com.mrizak.kernel.event.Event;
import com.mrizak.kernel.event.EventDispatcher;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberId;
import com.mrizak.register.domain.MemberRepository;
import com.mrizak.register.domain.MemberType;

public final class CreateMemberCommandHandler implements CommandHandler<CreateMember, MemberId> {

    private final MemberRepository memberRepository;
    private final EventDispatcher<Event> eventDispatcher;

    public CreateMemberCommandHandler(MemberRepository memberRepository, EventDispatcher<Event> eventDispatcher) {
        this.memberRepository = memberRepository;
        this.eventDispatcher = eventDispatcher;
    }

    public MemberId handle(CreateMember createMember) {
        final MemberId memberId = memberRepository.nextIdentity();
        MemberType memberType = MemberType.fromString(createMember.type);
        Member member = null;
        if (memberType == MemberType.STANDARD) {
            member = MemberFactory.createStandard(memberId, createMember.firstname, createMember.lastname);
        }
        if (memberType == MemberType.PREMIUM) {
            member = MemberFactory.createPremium(memberId, createMember.firstname, createMember.lastname);
        }
        memberRepository.save(member);
        eventDispatcher.dispatch(new CreateMemberEvent(memberId));
        return memberId;
    }
}
