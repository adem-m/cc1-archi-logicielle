package com.mrizak;

import com.mrizak.kernel.Clock;
import com.mrizak.kernel.DefaultSystemClock;
import com.mrizak.kernel.command.Command;
import com.mrizak.kernel.command.CommandBus;
import com.mrizak.kernel.command.CommandHandler;
import com.mrizak.kernel.command.SimpleCommandBus;
import com.mrizak.kernel.event.Event;
import com.mrizak.kernel.event.EventDispatcher;
import com.mrizak.kernel.event.EventListener;
import com.mrizak.kernel.query.Query;
import com.mrizak.kernel.query.QueryBus;
import com.mrizak.kernel.query.QueryHandler;
import com.mrizak.kernel.query.SimpleQueryBus;
import com.mrizak.payment.application.*;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.payment.domain.validation.PaymentValidationEngine;
import com.mrizak.payment.infra.InMemoryPaymentRepository;
import com.mrizak.register.application.*;
import com.mrizak.register.domain.MemberRepository;
import com.mrizak.register.infra.DefaultEventDispatcher;
import com.mrizak.register.infra.InMemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SpringConfiguration {

    @Bean
    public Clock clock() {
        return new DefaultSystemClock();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new InMemoryMemberRepository();
    }

    @Bean
    public RegistrationService registrationService() {
        return new RegistrationService(memberRepository());
    }

    @Bean
    public PaymentValidationEngine paymentValidationEngine() {
        return new PaymentValidationEngine(memberRepository());
    }

    @Bean
    public PaymentRepository paymentRepository() {
        return new InMemoryPaymentRepository();
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(paymentRepository(), paymentValidationEngine());
    }

    @Bean
    public PaymentFactory paymentFactory() {
        return new PaymentFactory(clock());
    }

    @Bean
    public EventDispatcher<Event> eventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(CreateMemberEvent.class, List.of(new CreateMemberEventListener()));
        listenerMap.put(CreatePaymentEvent.class, List.of(new CreatePaymentEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap = new HashMap<>();
        commandHandlerMap.put(CreateMember.class, new CreateMemberCommandHandler(memberRepository(), eventDispatcher()));
        commandHandlerMap.put(CreatePayment.class, new CreatePaymentCommandHandler(paymentRepository(), memberRepository(), paymentFactory(), clock()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(RetrieveMembers.class, new RetrieveMembersHandler(memberRepository()));
        queryHandlerMap.put(RetrieveMemberById.class, new RetrieveMemberByIdHandler(memberRepository()));
        queryHandlerMap.put(RetrievePaymentById.class, new RetrievePaymentByIdHandler(paymentRepository()));
        queryHandlerMap.put(RetrievePaymentsByMemberId.class, new RetrievePaymentsByMemberIdHandler(paymentRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }
}
