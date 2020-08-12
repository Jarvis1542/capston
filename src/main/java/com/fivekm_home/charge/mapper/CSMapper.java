package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.CS.*;
import com.fivekm_home.charge.domain.HP.HP_bookPage;
import com.fivekm_home.charge.domain.HP.HP_loadRes;
import com.fivekm_home.charge.domain.HP.HP_search;

import java.util.ArrayList;

public interface CSMapper {
    void csReg(CS_register cs_register); //충전소 등록
    ArrayList<CS_requestList> csRequestList();
    CS_requestPick csRequestPick(String chargeName);
    ArrayList<HP_loadRes> loadResidence(String email); // 거주지 불러오기
    void updateChargingChk(String chargeName); // 주차장 승인(주차장 요청 리스트에 n -> y
    ArrayList<CS_search> csSearchDataList(); // 카카오 지도에 마크 등 데이터 불러오기
    CS_bookPage csBookPage(String chargeName); // 해당 주차장 예약 페이지
}

