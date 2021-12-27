package com.mrizak.register.exposition;

import com.mrizak.kernel.command.CommandBus;
import com.mrizak.kernel.query.QueryBus;
import com.mrizak.register.application.RetrieveMemberById;
import com.mrizak.register.application.RetrieveMembers;
import com.mrizak.register.domain.Member;
import com.mrizak.register.domain.MemberId;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
}
