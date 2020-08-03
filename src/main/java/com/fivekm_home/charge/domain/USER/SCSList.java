package com.fivekm_home.charge.domain.USER;

import lombok.Data;

import java.util.Date;

@Data
public class SCSList {
    private Integer basicpay;
    private Integer overpay;
    private Integer BOOK_RESULT;
    private Date date_bookstart;
    private Date date_bookend;
}
