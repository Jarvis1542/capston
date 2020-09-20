package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.RES.Residence;
import com.fivekm_home.charge.domain.USER.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface MyPageMapper {
    int updatePassword(EditPassword editPassword);
    void regGuard(UpdateMem updateMem);
    void regRegister(UpdateMem updateMem);
    int checkResidence(String res_name);
    void regResidence(Residence residence);
    void regCar(RegCar regCar); // 차량 등록
    ArrayList<MemCarList> memCarList(String email); // 등록된 차량 불러오기
    ArrayList<RegCar> loadMyCar(String email); // 차 불러오기
    ArrayList<UserBookmark> userHpBookmark(String email); // 즐겨찾기 주차장 목록 불러오기
    int userHpBookmarkCnt(UserCriteria userCriteria); // 즐겨찾기 주차장 목록 총 갯수 - 페이징
    ArrayList<UserBookmark> userSCSBookmark(String email); // 즐겨찾기 충전소 목록 불러오기
    int userSCSBookmarkCnt(UserCriteria userCriteria); // 즐겨찾기 충전소 목록 총 갯수 - 페이징
    ArrayList<History> userSCSHistory(String email); // 충전소 이용 내역
    int userSCSHistoryCnt(UserCriteria userCriteria); // 충전소 이용 내역 총 갯수 - 페이징
    ArrayList<History> userHPHistory(String email); // 주차장 이용 내역
    ArrayList<History> userHPHistoryList(UserCriteria userCriteria); // 주차장 이용 내역 리스트 - 페이징
    int userHPHistoryCnt(UserCriteria userCriteria); // 주차장 이용 내역 총 갯수 - 페이징
    ArrayList<History> scsHistorySearchList(History history); // 충전소 이용 내역 날짜 검색
    int scsHistorySearchListCnt(UserCriteria userCriteria); // 충전소 이용 내역 날짜 검색 총 갯수 - 페이징
    ArrayList<History> hpHistorySearchList(History history); // 주차장 이용 내역 날짜 검색
    int hpHistorySearchListCnt(UserCriteria userCriteria); // 주차장 이용 내역 날짜 검색 총 갯수 - 페이징
}
