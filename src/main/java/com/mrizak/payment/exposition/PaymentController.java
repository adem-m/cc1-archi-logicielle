package com.mrizak.payment.exposition;

import com.mrizak.kernel.NoSuchEntityException;
import com.mrizak.kernel.command.CommandBus;
import com.mrizak.kernel.query.QueryBus;
import com.mrizak.payment.application.CreatePayment;
import com.mrizak.payment.application.IllegalPaymentDateException;
import com.mrizak.payment.application.RetrievePaymentById;
import com.mrizak.payment.application.RetrievePaymentsByMemberId;
import com.mrizak.payment.domain.Payment;
import com.mrizak.payment.domain.PaymentId;
import com.mrizak.payment.domain.PaymentType;
import com.mrizak.register.domain.MemberId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.net.URI;
import java.util.*;
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

    @PostMapping(value = "/payments", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> createPayment(@RequestBody @Valid PaymentRequest paymentRequest) {
        final CreatePayment createPayment = new CreatePayment(paymentRequest.memberId);
        final PaymentId paymentId = commandBus.send(createPayment);
        return ResponseEntity.created(URI.create("/payments/" + paymentId.getValue())).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            final String fieldName = ((FieldError) error).getField();
            final String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String, String> handleNoSuchException(MethodArgumentTypeMismatchException exception) {
        return Collections.singletonMap(
                "message",
                String.format(
                        "Invalid parameter type, %s required.",
                        Objects.requireNonNull(exception.getRequiredType()).getName()));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchEntityException.class)
    public Map<String, String> handleNoSuchException(NoSuchEntityException exception) {
        return Collections.singletonMap("message", exception.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalPaymentDateException.class)
    public Map<String, String> handleIllegalPaymentDateException(IllegalPaymentDateException exception) {
        return Collections.singletonMap("message", exception.getLocalizedMessage());
    }
}
