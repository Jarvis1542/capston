package com.fivekm_home.charge.domain.USER;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberList {
    private String email;
    private String name;
    private String phone;
    private LocalDateTime created_date;
//    private Role role;
}
