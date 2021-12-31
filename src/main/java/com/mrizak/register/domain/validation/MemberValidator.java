package com.mrizak.register.domain.validation;

import com.mrizak.register.domain.Member;

import java.util.function.Predicate;

public final class MemberValidator implements Predicate<Member> {
    private static final MemberValidator INSTANCE = new MemberValidator();

    private MemberValidator() {
    }

    public static MemberValidator getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean test(Member member) {
        return member.getFirstName().length() > 0 && member.getLastName().length() > 0;
    }
}
