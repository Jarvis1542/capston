package com.fivekm_home.charge.domain.DATE;

import lombok.Data;

import java.util.Date;

@Data
public class DateWeekDAO {
    private Integer SET_MON;
    private Integer SET_WEEK;
    private Date SET_WEEKSTART;
    private Date SET_WEEKEND;
}
