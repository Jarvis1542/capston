package com.fivekm_home.charge.domain.HP;

import lombok.Data;

@Data
public class HP_request {
    private String name;
    private String hp_name;
    private String hp_type;
    private String place;
    private String min30_fee;
    private String addMin10_fee;
    private String manage_time;
    private String post_code;
    private String road_addr;
    private String jibun_addr;
    private String detail_addr;
    private String extra_addr;
    private String apt_map;
    private String hp_pic;
    private String lat;
    private String lng;
}
