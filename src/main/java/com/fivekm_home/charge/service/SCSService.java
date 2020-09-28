package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.PAGING.Criteria;
import com.fivekm_home.charge.domain.SCS.*;
import com.fivekm_home.charge.domain.HP.HP_loadRes;
import com.fivekm_home.charge.mapper.HPMapper;
import com.fivekm_home.charge.mapper.SCSMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class SCSService {
    @Autowired(required = false)
    private SCSMapper scsMapper;

    //충전소 등록
    public void scsReg(SCS_reg scs_reg) {
        scsMapper.scsReg(scs_reg);
    }

    // 충전소 요청 리스트
    public ArrayList<SCS_requestList> scsRequestList(Criteria criteria){
        return scsMapper.scsRequestList(criteria);
    }

    // 충전소 요청 리스트 총 갯수 - 페이징
    public int scsRequestListCnt(){
        return scsMapper.scsRequestListCnt();
    }

    // 관리자에게 주차장 요청
    public SCS_request scsRequest(String scs_name) {
        return scsMapper.scsRequest(scs_name);
    }

    // 주차장 예약 페이지
    public SCS_bookPage scsBookPage(String scs_name){
        return scsMapper.scsBookPage(scs_name);
    }

    // 충전소 승인
    public void updateChargingChk(String scs_name){
        scsMapper.updateChargingChk(scs_name);
    }

    // 지도에 마크를 찍을 데이터 불러오기
    public ArrayList<SCS_search> scsSearchDataList(){
        return scsMapper.scsSearchDataList();
    }

    // 충전소 자리 변화 불러오기
    public ArrayList<SCS_chPlList> scsPlaceList(){
        return scsMapper.scsPlaceList();
    }

    // 예약버튼확인
    public int checkSCSBookBtn(String email, String scs_name){
        return scsMapper.checkSCSBookBtn(email, scs_name);
    }

    // 충전소 예약
    public void scsBook(SCS_book cs_book){
        scsMapper.scsBook(cs_book);
    }

    // 충전소 예약 체크 N -> y
    public void updateSCSBookCHk(SCS_pay scs_pay){
        scsMapper.updateSCSBookCHk(scs_pay);
    }

    // 충전소 scs_chk = y 카운터 + 1
    public void updateSCSCnt(){
        scsMapper.updateSCSCnt();
    }

    public void scsPayCnt(){
        scsMapper.scsPayCnt();
    }

    public int checkSCSBookCnt(){
        return scsMapper.checkSCSBookCnt();
    }

    public int checkSCSChk(SCS_pay scs_pay){
        return scsMapper.checkSCSChk(scs_pay);
    }

    // book_id fk 넣기
    public void scsPayBookIdUpdate(SCS_pay scs_pay){
        scsMapper.scsPayBookIdUpdate(scs_pay);
    }

    // 충전소 결제
    public void scsPay(SCS_pay cs_pay){
        scsMapper.scsPay(cs_pay);
    }

    // 충전소 예약 페이지에서 사용자가 즐겨찾기를 판단
    public int checkBookmark(SCS_bookmark scs_bookmark){
        return scsMapper.checkBookmark(scs_bookmark);
    }

    // 충전소 즐겨찾기 추가
    public void addSCSBookmark(SCS_bookmark scs_bookmark){
        scsMapper.addSCSBookmark(scs_bookmark);
    }

    // 충전소 즐겨찾기 삭제
    public void deleteSCSBookmark(SCS_bookmark scs_bookmark){
        scsMapper.deleteSCSBookmark(scs_bookmark);
    }

    // 충전소 지도검색
    public ArrayList<SCS_mapSearch> scsMapSearch(String scs_name){
        return scsMapper.scsMapSearch(scs_name);
    }

    // 예약취소
    public void scsBookCancel(SCS_book scs_book){
        scsMapper.scsBookCancel(scs_book);
    }

    // 충전소 타입 검색
    public ArrayList<SCS_search> typeFilter(ArrayList a){
        StringBuffer buffer = new StringBuffer();

        for (Object val: a) {
            buffer.append(val+"|");
        }

        String send = buffer.toString();
        String realSend = send.substring(0, send.length()-1);
        System.out.println("typeFilter realSend :" + send.substring(0, send.length()-1));
        return scsMapper.typeFilter(realSend);
    }

    // 충전소 타입 검색
    public ArrayList<SCS_chPlList> typePlaceFilter(ArrayList a){
        StringBuffer buffer = new StringBuffer();

        for (Object val: a) {
            buffer.append(val+"|");
        }

        String send = buffer.toString();
        String realSend = send.substring(0, send.length()-1);
        System.out.println("typePlaceFilter realSend :" + send.substring(0, send.length()-1));
        return scsMapper.typePlaceFilter(realSend);
    }
}
