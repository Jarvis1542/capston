package com.fivekm_home.charge.domain.HP;

import lombok.Data;

@Data
public class HP_search {
    private String hp_name;
    private String hp_type;
    private int min30_fee;
    private int addMin10_fee;
    private String road_addr;
    private String extra_addr;
    private String start_manage_time;
    private String end_manage_time;
    private int place;
    private String hp_chk;
    private String hp_pic;
    private double lat;
    private double lng;
}
