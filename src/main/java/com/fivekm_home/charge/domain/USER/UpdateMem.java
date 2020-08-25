package com.fivekm_home.charge.domain.USER;

import com.fivekm_home.charge.domain.USER.user.Role;
import lombok.Data;

@Data
public class UpdateMem {
    private String email;
    private String name;
    private String phone;
    private String mem_role;
    private String mp_co;
    private String mp_co_num;
    private String mp_lic;
    private String bank_name;
    private String acc_num;
    private String reg_lic;
    private Role role;
}
