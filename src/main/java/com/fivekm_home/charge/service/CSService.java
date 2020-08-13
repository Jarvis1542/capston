package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.CS.*;
import com.fivekm_home.charge.domain.HP.*;
import com.fivekm_home.charge.mapper.CSMapper;
import com.fivekm_home.charge.mapper.HPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CSService {
    @Autowired(required = false)
    private CSMapper csMapper;
    @Autowired(required = false)
    private HPMapper hpMapper;

    //충전소 등록
    public void csReg(CS_register cs_register) {
        csMapper.csReg(cs_register);
    }

    // 충전소 요청 리스트
    public ArrayList<CS_requestList> csRequestList(){
        return csMapper.csRequestList();
    }

    public CS_requestPick csRequestPick(String chargeName) {
        System.out.println("충전소 명 : " + chargeName);
        return csMapper.csRequestPick(chargeName);
    }

    // 주차장 예약 페이지
    public CS_bookPage csBookPage(String chargeName){
        return csMapper.csBookPage(chargeName);
    }

    // 거주지 불러오기
    public ArrayList<HP_loadRes> loadResidence(String email){
        return hpMapper.loadResidence(email);
    }

    // 충전소 승인
    public void updateChargingChk(String chargeName){
        csMapper.updateChargingChk(chargeName);
    }

    // 지도에 마크를 찍을 데이터 불러오기
    public ArrayList<CS_search> csSearchDataList(){
        return csMapper.csSearchDataList();
    }

    // 충전소 예약
    public void csBook(CS_book cs_book){
        csMapper.csBook(cs_book);
    }

    // 충전소 결제
    public void csPay(CS_pay cs_pay){
        System.out.println("CS_PAY : " + cs_pay.toString());
        csMapper.csPay(cs_pay);
    }

}
