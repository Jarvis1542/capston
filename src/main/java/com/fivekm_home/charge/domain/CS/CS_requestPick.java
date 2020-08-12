package com.fivekm_home.charge.domain.CS;

import lombok.Data;

@Data
public class CS_requestPick {
    private String CHARGENAME;      /* 충전소 이름 */
    private String OPERATION;       /* 운영 기관 */
    private String CHARGESPEED;     /* 충전 속도 */
    private String CHARGEAMOUNT;    /* 충전기 수 */
    private String POSTCODE;        /* 우편 번호 */
    private String ROADADDRESS;     /* 도로명 주소 */
    private String DETAILADDRESS;   /* 상세 주소 */
    private String EXTRAADDRESS;    /* 도로명 주소2 */
    private String MIN30FEE;        /* 기본 요금 */
    private String ADDMIN10FEE;     /* 추가 요금 */
    private String CABLE;           /* 케이블 */
    private String CHARGETYPE;      /* 충전 타입 */
    private String CHARGINGCHK;
    private String APTMAP; /*아파트 사진*/
    private String CHARGEPIC; /*충전소 사진*/
}
