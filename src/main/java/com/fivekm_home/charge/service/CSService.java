package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.CS.*;
import com.fivekm_home.charge.domain.HP.HP_request;
import com.fivekm_home.charge.mapper.CSMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CSService {
    @Autowired(required = false)
    private CSMapper csMapper;

    public void csReg(CS_register cs_register) {
        csMapper.csReg(cs_register);
    }

    // 충전소 요청 리스트
    public ArrayList<CS_requestList> csRequestList(){
        return csMapper.csRequestList();
    }

    public ArrayList<CS_requestPick> csRequestPick(String csName) {
        return csMapper.csRequestPick(csName);
    }

    // 충전소 승인
    public void updateChargingChk(String chargeName){
        csMapper.updateChargingChk(chargeName);
    }
}
