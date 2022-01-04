package com.mrizak.register.domain;

import com.mrizak.register.application.MemberFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MemberRepositoryTest {
    private MemberRepository memberRepository;

    @Before
    public void init() {
        memberRepository = new MemberRepositoryStub();
    }

    @Test
    public void should_add_and_retrieve_member() {
        Member member = MemberFactory.createStandard(MemberId.of(1), "Adem", "Mrizak");
        memberRepository.save(member);

        Assert.assertEquals(member, memberRepository.byId(member.getId()));
    }

    @Test
    public void should_retrieve_all_members() {
        Member member = MemberFactory.createStandard(MemberId.of(1), "Adem", "Mrizak");
        Member member2 = MemberFactory.createPremium(MemberId.of(2), "Paul", "Pierre");

        memberRepository.save(member);
        memberRepository.save(member2);

        List<Member> members = memberRepository.findAll();

        Assert.assertEquals(2, members.size());
        Assert.assertTrue(members.contains(member));
        Assert.assertTrue(members.contains(member2));
    }
}