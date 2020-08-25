package com.fivekm_home.charge.domain.HP;

import lombok.Data;

import java.util.Date;

@Data
public class HP_book {
    private String hp_name;
    private String email;
    private Date start_date;
    private Date end_date;
}
