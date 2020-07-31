package com.fivekm_home.charge.domain.RES;

import lombok.Data;

@Data
public class Residence {
    private String resName;
    private String postcode;
    private String roadAddress;
    private String jibunAddress;
    private String extraAddress;
    private String detailAddress;
    private String lat;
    private String lng;
    private String email;
}