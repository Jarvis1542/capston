package com.fivekm_home.charge.domain.USER;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.user.Role;
import lombok.Data;

@Data
public class MemberEdit {
    private String email;
    private String name;
    private String picture;
    private Role role;
    private String phone;

    public MemberEdit(SessionUser sessionUser){
        this.email = sessionUser.getEmail();
        this.name = sessionUser.getEmail();
        this.phone = sessionUser.getEmail();
        this.role = sessionUser.getRole();
        this.phone = sessionUser.getPhone();
    }
}
