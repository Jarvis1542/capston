package com.fivekm_home.charge.domain.OS;

import lombok.Data;

import java.util.Date;

@Data
public class QBReplyList {
    private long rno;
    private String rcontent;
    private String rwriter;
    private Date rregDate;
    private long bno;
    private long mbo;
}

