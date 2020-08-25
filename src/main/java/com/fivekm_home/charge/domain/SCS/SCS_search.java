package com.fivekm_home.charge.domain.SCS;

import lombok.Data;

@Data
public class SCS_search {
    private String scs_name;
    private String oper_ins;
    private String scs_amount;
    private int min30_fee;
    private int addMin10_fee;
    private String road_addr;
    private String extra_addr;
    private String manage_time;
    private String scs_pic;
    private double lat;
    private double lng;
    private String scs_chk;
}
