package com.development.project.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserPostRequests {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
