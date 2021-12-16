package com.mrizak;

import com.mrizak.payment.application.PaymentService;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.payment.infra.InMemoryPaymentRepository;
import com.mrizak.register.application.RegistrationService;
import com.mrizak.register.domain.MemberRepository;
import com.mrizak.register.infra.InMemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public MemberRepository memberRepository() {
        return new InMemoryMemberRepository();
    }

    @Bean
    public RegistrationService registrationService() {
        return new RegistrationService(memberRepository());
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new InMemoryPaymentRepository();
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(paymentRepository(), memberRepository());
    }
}
