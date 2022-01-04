package com.mrizak.payment.application;

import com.mrizak.kernel.Clock;
import com.mrizak.kernel.command.CommandHandler;
import com.mrizak.payment.domain.InitialPayment;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberId;
import com.mrizak.register.domain.MemberRepository;

import java.util.List;

public final class CreatePaymentCommandHandler implements CommandHandler<CreatePayment, PaymentId> {
    private final PaymentRepository paymentRepository;
    private final MemberRepository memberRepository;
    private final PaymentFactory paymentFactory;
    private final Clock clock;

    public CreatePaymentCommandHandler(PaymentRepository paymentRepository, MemberRepository memberRepository, PaymentFactory paymentFactory, Clock clock) {
        this.paymentRepository = paymentRepository;
        this.memberRepository = memberRepository;
        this.paymentFactory = paymentFactory;
        this.clock = clock;
    }

    @Override
    public PaymentId handle(CreatePayment createPayment) {
        Member member = memberRepository.byId(MemberId.of(Integer.parseInt(createPayment.memberId)));
        List<Payment> payments = paymentRepository.byMemberId(member.getId());
        Payment payment;
        if (payments.isEmpty()) {
            payment = paymentFactory.createInitialPayment(paymentRepository.nextIdentity(), member);
        } else {
            Payment lastPayment = paymentRepository.lastPaymentOfMember(member.getId());
            if (lastPayment instanceof InitialPayment || lastPayment.getDateTime().isBefore(clock.now().minusMonths(1))) {
                payment = paymentFactory.createMonthlyPayment(paymentRepository.nextIdentity(), member);
            } else {
                throw new IllegalPaymentDateException();
            }
        }
        paymentRepository.save(payment);
        return payment.getId();
    }
}
