package com.mrizak.register.exposition;

import java.util.List;

public class MembersResponse {
    public final List<MemberResponse> members;

    public MembersResponse(List<MemberResponse> members) {
        this.members = members;
    }
}