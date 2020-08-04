package com.fivekm_home.charge.domain.HP;

import lombok.Data;

import java.util.Date;

@Data
public class HP_reg {
    private String parkingName;
    private String parkingType;
    private String etc;
    private int place;
    private int min30Fee;
    private int addMin10Fee;
    private String manageTime;
    private String parkingPic;
    private String aptMap;
    private String resName;
}
