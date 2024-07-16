package com.company.dto;

import com.company.enums.Role;
import com.company.enums.Status;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private Status status;
    private Role role;
    private String jwt;
}
