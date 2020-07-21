package com.fivekm_home.charge.domain.HP;

import lombok.Data;

@Data
public class HP_request {
    private String parkingName;
    private String parkingType;
    private String min30Fee;
    private String addMin10Fee;
    private String place;
    private String sample4_postcode;
    private String sample4_roadAddress;
    private String sample4_jibunAddress;
    private String sample4_detailAddress;
    private String sample4_extraAddress;
    private String dayTime;
    private String weeTime;
    private String holTime;
    private String aptMap;
    private String parkingPic;
}
