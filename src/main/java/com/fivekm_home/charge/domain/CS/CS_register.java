package com.fivekm_home.charge.domain.CS;

import lombok.Data;

@Data
public class CS_register {

    private String chargeName;//충전소이름
    private String operation; //운영기관
    private String chargeSpeed; //충전속도
    private String chargeAmount; //충전기 수
    private String min30Fee; //기본요금
    private String addMin10Fee; //10분당 추가요금
    private String manageTime; //운영시간
    private String chargePic; //사진1
    private String aptMap; //사진 2
    private String cable; //케이블유무
    private String chargeType; //충전소타입
    private String resName;



}
