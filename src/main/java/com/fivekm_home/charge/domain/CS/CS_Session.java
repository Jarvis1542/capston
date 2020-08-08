package com.fivekm_home.charge.domain.CS;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
public class CS_Session implements Serializable { // 이 클래스는 인증된 사용자 정보만 필요
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

    // 소셜 로그인
    public CS_Session(ArrayList<CS_requestList> csRequestList){
        this.CHARGENAME = csRequestList.get(0).getCHARGENAME();
        this.OPERATION = csRequestList.get(0).getOPERATION();
        this.CHARGESPEED = csRequestList.get(0).getCHARGESPEED();
        this.CHARGEAMOUNT = csRequestList.get(0).getCHARGEAMOUNT();
        this.POSTCODE = csRequestList.get(0).getPOSTCODE();
        this.ROADADDRESS = csRequestList.get(0).getROADADDRESS();
        this.DETAILADDRESS = csRequestList.get(0).getDETAILADDRESS();
        this.EXTRAADDRESS = csRequestList.get(0).getEXTRAADDRESS();
        this.MIN30FEE = csRequestList.get(0).getMIN30FEE();
        this.ADDMIN10FEE = csRequestList.get(0).getADDMIN10FEE();
        this.CABLE = csRequestList.get(0).getCABLE();
        this.CHARGETYPE = csRequestList.get(0).getCHARGETYPE();
    }
}
