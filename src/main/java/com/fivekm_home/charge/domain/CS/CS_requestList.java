package com.fivekm_home.charge.domain.CS;

import lombok.Data;

@Data
public class CS_requestList {
    private String CHARGENAME;
    private String OPERATION;
    private String CHARGESPEED;
    private String CHARGEAMOUNT;
    private String POSTCODE;
    private String ROADADDRESS;
    private String DETAILADDRESS;
    private String EXTRAADDRESS;
    private String MIN30FEE;
    private String ADDMIN10FEE;
    private String CABLE;
    private String CHARGETYPE;
}
