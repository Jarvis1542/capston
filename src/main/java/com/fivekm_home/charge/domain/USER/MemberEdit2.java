package com.fivekm_home.charge.domain.USER;

import com.fivekm_home.charge.domain.USER.user.Role;
import lombok.Data;

@Data
public class MemberEdit2 {
    private String email;
    private String name;
    private String picture;
    private Role role;
    private String phone;
    private String car_id;
    private String car_name;
    private String car_model;
    private String car_scs_type;
}
