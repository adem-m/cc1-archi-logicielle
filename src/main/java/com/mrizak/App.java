package com.mrizak;

import com.mrizak.payment.application.PaymentService;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.payment.application.PaymentFactory;
import com.mrizak.register.application.RegistrationService;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberRepository;
import com.mrizak.register.application.MemberFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(App.class);
        springApplication.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        final ConfigurableApplicationContext applicationContext = springApplication.run(args);

        MemberRepository memberRepository = applicationContext.getBean(MemberRepository.class);
        RegistrationService registrationService = applicationContext.getBean(RegistrationService.class);
        Member member = MemberFactory.createStandard(
                memberRepository.nextIdentity(),
                "Adem",
                "Mrizak");
        registrationService.registerMember(member);

        PaymentRepository paymentRepository = applicationContext.getBean(PaymentRepository.class);
        PaymentService paymentService = applicationContext.getBean(PaymentService.class);
        Payment payment = PaymentFactory.createInitialPayment(
                paymentRepository.nextIdentity(),
                member
        );
        paymentService.proceedInitialPayment(payment);
    }
}
