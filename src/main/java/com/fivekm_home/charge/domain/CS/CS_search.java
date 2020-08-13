package com.fivekm_home.charge.domain.CS;

import lombok.Data;


@Data
public class CS_search  {
    private String chargeName;
    private String OPERATION;
    private String chargeAmount;
    private int min30Fee;
    private int addMin10Fee;
    private String roadAddress;
    private String extraAddress;
    private String manageTime;
    private String chargePic;
    private double lat;
    private double lng;
    private String chargingChk;

}
