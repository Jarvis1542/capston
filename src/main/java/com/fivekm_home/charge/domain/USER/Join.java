package com.fivekm_home.charge.domain.USER;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Join {
    private String email;
    private String password;
    private String name;
    private String phone;
}

