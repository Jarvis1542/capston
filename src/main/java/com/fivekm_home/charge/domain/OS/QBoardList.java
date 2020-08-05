package com.fivekm_home.charge.domain.OS;

import lombok.Data;

import java.util.Date;

@Data
public class QBoardList {
    private long bno;
    private String title;
    private String writer;
    private String content;
    private Date regDate;
    private int noCount;
    private int noReco;
}
