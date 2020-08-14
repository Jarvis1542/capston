package com.fivekm_home.charge.domain.CS;

import lombok.Data;

import java.util.Date;

@Data
public class CS_book {
    private String chargeName;
    private String email;
    private Date start_time;
    private Date end_time;
    private Date startUseTime;
    private Date endUseTime;
}
