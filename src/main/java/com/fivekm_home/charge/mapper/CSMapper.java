package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.CS.*;

import java.util.ArrayList;

public interface CSMapper {
    void csReg(CS_register cs_register);
    ArrayList<CS_requestList> csRequestList();
    ArrayList<CS_requestPick> csRequestPick(String csName);
    void updateChargingChk(String chargeName); // 주차장 승인(주차장 요청 리스트에 n -> y
}

