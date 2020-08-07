package com.fivekm_home.charge.domain.CS;

import lombok.Data;

import java.util.Date;

@Data
public class CS_requestList {
    private String csName;
    private String csType;
    private String place;
    private String resName;
    private String roadAddress;
    private String jibunAddress;
    private String detailAddress;
    private Date regDate;
    private String name;
    private String csChk;
}
