package com.company.dto;

import com.company.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtDTO {
    private Integer id;
    private String username;
    private Role role;

    public JwtDTO(Integer id, String userName, Role role) {
        this.id = id;
        this.username = userName;
        this.role = role;
    }

    public JwtDTO(Integer id) {
        this.id = id;
    }
}
