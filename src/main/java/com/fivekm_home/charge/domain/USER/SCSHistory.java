package com.fivekm_home.charge.domain.USER;

import lombok.Data;

import java.util.Date;

@Data
public class SCSHistory {
    private String scs_name;
    private String road_addr;
    private Date start_date;
    private Date end_date;
    private int price;
    private Date pay_date;
    private String email;
    private int usedTime;
}
