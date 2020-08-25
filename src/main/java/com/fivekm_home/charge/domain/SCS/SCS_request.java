package com.fivekm_home.charge.domain.SCS;

import lombok.Data;

@Data
public class SCS_request {
    private String name;
    private String scs_name;      /* 충전소 이름 */
    private String oper_ins;       /* 운영 기관 */
    private String scs_speed;     /* 충전 속도 */
    private String scs_amount;    /* 충전기 수 */
    private String post_code;        /* 우편 번호 */
    private String jibun_addr;
    private String road_addr;     /* 도로명 주소 */
    private String detail_addr;   /* 상세 주소 */
    private String extra_addr;    /* 도로명 주소2 */
    private String min30_fee;        /* 기본 요금 */
    private String addmin10_fee;     /* 추가 요금 */
    private String cable;           /* 케이블 */
    private String scs_type;      /* 충전 타입 */
    private String scs_chk;
    private String apt_map; /*아파트 사진*/
    private String scs_pic; /*충전소 사진*/
    private String lat;
    private String lng;
}
