package com.mrizak;

import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.payment.domain.PaymentService;
import com.mrizak.payment.infra.PSQLPaymentRepository;
import com.mrizak.payment.infra.PaymentFactory;
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

        PaymentRepository paymentRepository = new PSQLPaymentRepository();
        PaymentService paymentService = new PaymentService(paymentRepository, memberRepository);
        Payment payment = PaymentFactory.createInitialPayment(
                paymentRepository.nextIdentity(),
                member
        );
        paymentService.proceedInitialPayment(payment);
    }
}
