package com.fivekm_home.charge.domain.SCS;

import lombok.Data;

import java.util.Date;

@Data
public class SCS_requestList {
    private String scs_name;      /* 충전소 이름 */
    private String scs_type;      /* 충전 타입 */
    private String scs_amount;    /* 충전기 수 */
    private String res_name;
    private String road_addr;     /* 도로명 주소 */
    private String jibun_addr;    /* 지번 주소 */
    private String detail_addr;   /* 상세 주소 */
    private Date reg_date;
    private String name;
    private String scs_chk;
}
