package com.fivekm_home.charge.domain.USER;

import lombok.Data;

@Data
public class UserBookmark {
    private String email;
    private String hp_name;
    private String hp_pic;
    private String road_addr;
    private String extra_addr;
    private String start_manage_time;
    private String end_manage_time;
    private String min30_fee;
    private String addmin10_fee;
    private String scs_name;
    private String scs_pic;
}
