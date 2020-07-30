package com.fivekm_home.charge.domain.USER;

import com.fivekm_home.charge.domain.USER.user.Role;
import lombok.Data;

@Data
public class UpdateMem {
    private String email;
    private String name;
    private String phone;
    private String memberRole;
    private String guardCo;
    private String guardCoNum;
    private String guardLicence;
    private String bankName;
    private String accountNum;
    private String registerLicence;
    private Role role;
}
