package com.fivekm_home.charge.domain.CS;

import lombok.Data;

@Data
public class CS_register {

    private String chargeName;//충전소이름
    private String operation; //운영기관
    private String operationTime;//운영시간
    private String chargeSpeed; //충전속도
    private String postcode; //우편번호
    private String roadAddress; //도로주소
    private String detailAddress; //상세주소
    private String extraAddress; //참고항목
    private String image1; //사진1
    private String image2; //사진 2
    private String cable; //케이블유무
    private String chargeType; //충전소타입



}
