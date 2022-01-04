package com.mrizak.payment.domain;

import com.mrizak.payment.application.PaymentFactory;
import com.mrizak.register.application.MemberFactory;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private final PaymentFactory paymentFactory = new PaymentFactory(new FakeClock());

    private final Member member = MemberFactory.createStandard(MemberId.of(1), "Adem", "Mrizak");


    @Before
    public void init() {
        paymentRepository = new PaymentRepositoryStub();
    }

    @Test
    public void should_add_and_retrieve_payment() {
        InitialPayment initialPayment = paymentFactory.createInitialPayment(new PaymentId(1), member);

        paymentRepository.save(initialPayment);

        Assert.assertEquals(initialPayment, paymentRepository.byId(initialPayment.getId()));
    }

    @Test
    public void should_retrieve_every_payments_of_member() {
        InitialPayment initialPayment = paymentFactory.createInitialPayment(new PaymentId(1), member);
        MonthlyPayment monthlyPayment = paymentFactory.createMonthlyPayment(new PaymentId(2), member);

        paymentRepository.save(initialPayment);
        paymentRepository.save(monthlyPayment);

        List<Payment> payments = paymentRepository.byMemberId(member.getId());

        Assert.assertEquals(2, payments.size());
        Assert.assertTrue(payments.contains(initialPayment));
        Assert.assertTrue(payments.contains(monthlyPayment));
    }

}