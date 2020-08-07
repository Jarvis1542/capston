package com.fivekm_home.charge.domain.HP;

import lombok.Data;

import java.util.Date;

@Data
public class HP_book {
    private String parkingName;
    private String email;
    private Date startDate;
    private Date endDate;
    private Date startUseTime;
    private Date endUseTime;
}
