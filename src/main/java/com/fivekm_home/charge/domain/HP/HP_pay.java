package com.fivekm_home.charge.domain.HP;

import lombok.Data;

import java.util.Date;

@Data
public class HP_pay {
    private String hp_name;
    private String price;
    private String email;
    private String user_name;
    private String phone;
    private String car_num;
    private Date start_date;
}
