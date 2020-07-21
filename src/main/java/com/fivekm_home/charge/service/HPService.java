package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.HP.HP_book;
import com.fivekm_home.charge.domain.HP.HP_register;
import com.fivekm_home.charge.domain.HP.HP_request;
import com.fivekm_home.charge.domain.HP.HP_requestList;
import com.fivekm_home.charge.mapper.HPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HPService {
    @Autowired(required = false)
    private HPMapper hpMapper;

    // 주차장 등록
    public void hpReg(HP_register hp_register){
        hpMapper.hpReg(hp_register);
    }

    // 관리자에게 주차장 요청
    public HP_request hpRequest(String parkingName){
        return hpMapper.hpRequest( parkingName);
    }

    // 주차장 요청 리스트
    public ArrayList<HP_requestList> hpRequestList(){
        return hpMapper.hpRequestList();
    }

    // 주차장 예약
    public HP_book hpBook(){
        return hpMapper.hpBook();
    }
}
