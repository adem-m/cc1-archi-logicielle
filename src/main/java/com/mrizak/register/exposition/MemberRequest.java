package com.mrizak.register.exposition;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MemberRequest {
    @NotNull
    @NotBlank
    public String type;

    @NotNull
    @NotBlank
    public String firstname;

    @NotNull
    @NotBlank
    public String lastname;
}
