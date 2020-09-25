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

    // 충전소 예약
    public void scsBook(SCS_book cs_book){
        scsMapper.scsBook(cs_book);
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

    // 충전소 타입 검색
    public ArrayList<SCS_type> scsType(Asdf asdf){

//        scs_type = "asdf, qwer";
//        String type_str[] = scs_type.split(",");
//        String type1 = type_str[0];
//        String typ2 = type_str[1];
//        Asdf asdf = new Asdf();
//        asdf.setTpye1 =type1;
//
//        type1.setType1(type1);
//        if (type1.isEmpty()!= null && typ2.isEmpty() == null){
//            return scsMap
//        }
//        if (type1.isEmpty()!= null && typ2.isEmpty() != null){
//            return scsMapper.scsType2();
//        }
        return scsMapper.scsType1(asdf);
    }


}
