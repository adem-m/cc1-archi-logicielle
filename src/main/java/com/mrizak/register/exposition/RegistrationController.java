package com.mrizak.register.exposition;

import com.mrizak.kernel.NoSuchEntityException;
import com.mrizak.kernel.command.CommandBus;
import com.mrizak.kernel.query.QueryBus;
import com.mrizak.register.application.CreateMember;
import com.mrizak.register.application.RetrieveMemberById;
import com.mrizak.register.application.RetrieveMembers;
import com.mrizak.register.domain.Member;
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
public class RegistrationController {

    private final CommandBus commandBus;
    private final QueryBus queryBus;

    public RegistrationController(CommandBus commandBus, QueryBus queryBus) {
        this.commandBus = commandBus;
        this.queryBus = queryBus;
    }

    @GetMapping(value = "/members", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MembersResponse> getAll() {
        final List<Member> members = queryBus.send(new RetrieveMembers());
        MembersResponse membersResponse =
                new MembersResponse(members.stream().map(member ->
                        new MemberResponse(
                                member.getId().getValue(),
                                member.getFirstName(),
                                member.getLastName()
                        )).collect(Collectors.toList()));
        return ResponseEntity.ok(membersResponse);
    }

    @GetMapping(value = "/members/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<MemberResponse> getById(@PathVariable int id) {
        final MemberId memberId = MemberId.of(id);
        Member member = queryBus.send(new RetrieveMemberById(memberId));
        MemberResponse memberResponse = new MemberResponse(
                member.getId().getValue(),
                member.getFirstName(),
                member.getLastName());
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping(value = "/members", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> create(@RequestBody @Valid MemberRequest memberRequest) {
        CreateMember createMember = new CreateMember(memberRequest.firstname, memberRequest.lastname);
        MemberId memberId = commandBus.send(createMember);
        return ResponseEntity.created(URI.create("/members/" + memberId.getValue())).build();
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
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
}
