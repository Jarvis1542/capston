package com.fivekm_home.charge.domain.HP;

import lombok.Data;

import java.util.Date;

@Data
public class HP_reg {
    private String hp_name;
    private String hp_type;
    private int place;
    private int min30_fee;
    private int addMin10_fee;
    private String start_manage_time;
    private String end_manage_time;
    private String hp_pic;
    private String apt_map;
    private String res_name;
}
