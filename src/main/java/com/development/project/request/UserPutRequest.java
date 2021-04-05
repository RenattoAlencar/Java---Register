package com.development.project.request;

import lombok.Data;

@Data
public class UserPutRequest {
    private Long id;
    private String name;
    private String email;
    private String password;

}
