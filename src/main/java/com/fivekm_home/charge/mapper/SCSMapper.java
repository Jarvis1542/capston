package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.HP.HP_loadRes;
import com.fivekm_home.charge.domain.SCS.*;

import java.util.ArrayList;

public interface SCSMapper {
    void scsReg(SCS_reg scs_reg); //충전소 등록
    ArrayList<SCS_requestList> scsRequestList();
    SCS_request scsRequest(String scs_name);
    ArrayList<HP_loadRes> loadResidence(String email); // 거주지 불러오기
    void updateChargingChk(String scs_name); // 주차장 승인(주차장 요청 리스트에 n -> y
    ArrayList<SCS_search> scsSearchDataList(); // 카카오 지도에 마크 등 데이터 불러오기
    SCS_bookPage scsBookPage(String scs_name); // 해당 주차장 예약 페이지
    void scsPay(SCS_pay scs_pay);
    void scsBook(SCS_book scs_book); // 충전소 예약
    int checkBookmark(SCS_bookmark scs_bookmark); // 충전소 예약 페이지에서 사용자가 즐겨찾기를 판단
    void addSCSBookmark(SCS_bookmark scs_bookmark); // 충전소 즐겨찾기 추가
    void deleteSCSBookmark(SCS_bookmark scs_bookmark); // 충전소 즐겨찾기 삭제
}
