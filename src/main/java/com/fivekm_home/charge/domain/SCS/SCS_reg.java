package com.fivekm_home.charge.domain.SCS;

import lombok.Data;

@Data
public class SCS_reg {
    private String scs_name;//충전소이름
    private String oper_ins; //운영기관
    private String scs_speed; //충전속도
    private String scs_amount; //충전기 수
    private String min30_fee; //기본요금
    private String addMin10_fee; //10분당 추가요금
    private String manage_time; //운영시간
    private String scs_pic; //사진1
    private String apt_map; //사진 2
    private String cable; //케이블유무
    private String scs_type; //충전소타입
    private String res_name;
}
