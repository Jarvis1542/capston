package com.fivekm_home.charge.domain.SCS;

import lombok.Data;

import java.util.Date;

@Data
public class SCS_book {
    private String scs_name;
    private String email;
    private String car_num;
    private Date start_date;
    private Date end_date;
}
