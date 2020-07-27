package com.fivekm_home.charge.domain.USER;

import com.fivekm_home.charge.domain.USER.user.Role;
import lombok.Data;

@Data
public class Login {
    private String email;
    private String password;
    private String name;
    private Role role;
    private String picture;
    private String phone;
}
