package com.mrizak.register.application;

import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberId;
import com.mrizak.register.domain.PremiumMember;
import com.mrizak.register.domain.StandardMember;
import com.mrizak.register.domain.validation.InvalidMemberException;
import com.mrizak.register.domain.validation.MemberValidator;

public final class MemberFactory {
    public static Member createStandard(MemberId id, String firstName, String lastName) {
        final Member member = new StandardMember(id, firstName, lastName);
        if (MemberValidator.getInstance().test(member)) {
            return member;
        }
        throw new InvalidMemberException();
    }

    public static Member createPremium(MemberId id, String firstName, String lastName) {
        final Member member = new PremiumMember(id, firstName, lastName);
        if (MemberValidator.getInstance().test(member)) {
            return member;
        }
        throw new InvalidMemberException();
    }
}
