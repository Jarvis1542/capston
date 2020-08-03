package com.fivekm_home.charge.domain.HP;

import lombok.Data;

@Data
public class HP_search {
    private String parkingName;
    private String parkingType;
    private int min30Fee;
    private int addMin10Fee;
    private String roadAddress;
    private String extraAddress;
    private String manageTime;
    private int place;
    private String parkingChk;
    private String parkingPic;
    private double lat;
    private double lng;
}
