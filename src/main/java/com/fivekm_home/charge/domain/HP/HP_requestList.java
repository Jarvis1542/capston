package com.fivekm_home.charge.domain.HP;

import lombok.Data;

import java.util.Date;

@Data
public class HP_requestList {
    private String hp_name;
    private String hp_type;
    private String place;
    private String res_name;
    private String road_addr;
    private String jibun_addr;
    private String detail_addr;
    private Date reg_date;
    private String name;
    private String hp_chk;
}