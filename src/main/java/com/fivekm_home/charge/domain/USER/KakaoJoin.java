package com.fivekm_home.charge.domain.USER;

import com.fivekm_home.charge.domain.USER.user.Role;
import lombok.Data;

@Data
public class KakaoJoin {
    private String email;
    private String name;
    private String picture;
    private Role role;
    private String phone;
}
