package com.learning.security.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Integer id;
    private String name;
    private String password;
    private String email;
    private List<RoleDTO> roles;

}
