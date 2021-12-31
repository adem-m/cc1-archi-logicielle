package com.mrizak.payment.exposition;

import com.mrizak.kernel.command.CommandBus;
import com.mrizak.kernel.query.QueryBus;
import com.mrizak.payment.application.RetrievePaymentById;
import com.mrizak.payment.application.RetrievePaymentsByMemberId;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.payment.domain.PaymentType;
import com.mrizak.register.domain.MemberId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public final class PaymentController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public PaymentController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @GetMapping(value = "/payments/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaymentResponse> getById(@PathVariable int id) {
        final PaymentId paymentId = new PaymentId(id);
        final Payment payment = queryBus.send(new RetrievePaymentById(paymentId));
        final PaymentResponse paymentResponse = new PaymentResponse(
                payment.getId().getValue(),
                payment.getMemberId().getValue(),
                payment.getAmount().getValue(),
                payment.getDateTime().toString(),
                PaymentType.fromClass(payment.getClass()).getValue()
        );
        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping(value = "/payments/member/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PaymentsResponse> getByMemberId(@PathVariable int id) {
        final MemberId memberId = MemberId.of(id);
        final List<Payment> payments = queryBus.send(new RetrievePaymentsByMemberId(memberId));
        final PaymentsResponse paymentsResponse = new PaymentsResponse(
                payments.stream().map(payment ->
                        new PaymentResponse(
                                payment.getId().getValue(),
                                payment.getMemberId().getValue(),
                                payment.getAmount().getValue(),
                                payment.getDateTime().toString(),
                                PaymentType.fromClass(payment.getClass()).getValue()
                        )).collect(Collectors.toList()));
        return ResponseEntity.ok(paymentsResponse);
    }
}
