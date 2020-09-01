package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.HP.*;

import java.util.ArrayList;

public interface HPMapper {
    ArrayList<HP_loadRes> loadResidence(String email); // 거주지 불러오기
    void hpReg(HP_reg hp_reg); // 주차장 등록
    HP_request hpRequest(String parkingName); // 주차장 요청 목록에서 해당 주차장 불러오기
    ArrayList<HP_requestList> hpRequestList(); // 주차장 요청 리스트
    void updateParkingChk(String parkingName); // 주차장 승인(주차장 요청 리스트에 n -> y
    ArrayList<HP_search> hpSearchDataList(); // 카카오 지도에 마크 등 데이터 불러오기
    HP_bookPage hpBookPage(String parkingName); // 해당 주차장 예약 페이지
    void hpBook(HP_book hp_book); // 주차장 예약
    void hpPay(HP_pay hp_pay); // 주차장 결제
    int checkBookmark(HP_bookmark hp_bookmark); // 주차장 예약 페이지에서 사용자가 즐겨찾기를 판단
    void addHpBookmark(HP_bookmark hp_bookmark); // 주차장 즐겨찾기 추가
    void deleteHpBookmark(HP_bookmark hp_bookmark); // 주차장 즐겨찾기 삭제
}
