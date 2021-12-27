package com.mrizak;

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
import com.mrizak.payment.application.PaymentService;
import com.mrizak.payment.domain.PaymentRepository;
import com.mrizak.payment.infra.InMemoryPaymentRepository;
import com.mrizak.register.application.*;
import com.mrizak.register.domain.MemberRepository;
import com.mrizak.register.infra.DefaultEventDispatcher;
import com.mrizak.register.infra.InMemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Bean
    public EventDispatcher<Event> eventDispatcher() {
        final Map<Class<? extends Event>, List<EventListener<? extends Event>>> listenerMap = new HashMap<>();
        listenerMap.put(CreateMemberEvent.class, List.of(new CreateMemberEventListener()));
        return new DefaultEventDispatcher(listenerMap);
    }

    @Bean
    public CommandBus commandBus() {
        final Map<Class<? extends Command>, CommandHandler> commandHandlerMap =
                Collections.singletonMap(CreateMember.class, new CreateMemberCommandHandler(memberRepository(), eventDispatcher()));
        return new SimpleCommandBus(commandHandlerMap);
    }

    @Bean
    public QueryBus queryBus() {
        final Map<Class<? extends Query>, QueryHandler> queryHandlerMap = new HashMap<>();
        queryHandlerMap.put(RetrieveMembers.class, new RetrieveMembersHandler(memberRepository()));
        queryHandlerMap.put(RetrieveMemberById.class, new RetrieveMemberByIdHandler(memberRepository()));
        return new SimpleQueryBus(queryHandlerMap);
    }
}
