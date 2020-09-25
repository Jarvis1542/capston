package com.fivekm_home.charge.domain.SCS;

import lombok.Data;

import java.util.Date;

@Data
public class SCS_pay {
    private String scs_name;
    private String price;
    private String email;
    private String user_name;
    private String phone;
    private String car_num;
    private Date start_date;
}
