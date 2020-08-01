package com.fivekm_home.charge.domain.HP;

import lombok.Data;

import java.util.Date;

@Data
public class HP_requestList {
    private String parkingName;
    private String parkingType;
    private String place;
    private String resName;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private Date regDate;
    private String name;
    private String parkingChk;
}