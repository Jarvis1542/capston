package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.HP.*;
import com.fivekm_home.charge.mapper.HPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class HPService {
    @Autowired(required = false)
    private HPMapper hpMapper;

    // 거주지 불러오기
    public ArrayList<HP_loadRes> loadResidence(String email){
        return hpMapper.loadResidence(email);
    }
    // 주차장 등록
    public void hpReg(HP_reg hp_reg){
        hpMapper.hpReg(hp_reg);
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
