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
        System.out.println("서비스 쪽 parkingName : " + parkingName);
        return hpMapper.hpRequest(parkingName);
    }

    // 주차장 요청 리스트
    public ArrayList<HP_requestList> hpRequestList(){
        return hpMapper.hpRequestList();
    }

    // 주차장 예약 페이지
    public HP_bookPage hpBookPage(String parkingName){
        return hpMapper.hpBookPage(parkingName);
    }

    // 주차장 승인
    public void updateParkingChk(String parkingName){
        hpMapper.updateParkingChk(parkingName);
    }

    // 지도에 마크를 찍을 데이터 불러오기
    public ArrayList<HP_search> hpSearchDataList(){
        return hpMapper.hpSearchDataList();
    }

    // 주차장 즐겨찾기 추가
    public void addBookmark(HP_bookmark hp_bookmark){
        hpMapper.addBookmark(hp_bookmark);
    }

    // 주차장 즐겨찾기 삭제
    public void deleteBookmark(HP_bookmark hp_bookmark){
        hpMapper.deleteBookmark(hp_bookmark);
    }

    // 주차장 예약 페이지에서 사용자가 즐겨찾기를 판단
    public int checkBookmark(HP_chkBookmark hp_chkBookmark){
        return hpMapper.checkBookmark(hp_chkBookmark);
    }

    // 주차장 예약
    public void hpBook(HP_book hp_book){
        hpMapper.hpBook(hp_book);
    }

    // 주차장 결제
    public void hpPay(HP_pay hp_pay){
        System.out.println("HP_PAY : " + hp_pay.toString());
        hpMapper.hpPay(hp_pay);
    }
}
