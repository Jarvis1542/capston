package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.RES.Residence;
import com.fivekm_home.charge.domain.USER.*;
import com.fivekm_home.charge.mapper.MemMapper;
import com.fivekm_home.charge.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MyPageService {
    @Autowired(required = false)
    MemMapper memMapper;
    @Autowired(required = false)
    MyPageMapper myPageMapper;

    public MemberEdit2 memberEdit(MemberEdit memberEdit){
        return memMapper.MemberEdit(memberEdit);
    }

    // 비밀번호 업데이트
    public int updatePassword(EditPassword editPassword){
        return myPageMapper.updatePassword(editPassword);
    }

    // 경비 등록
    public void regGuard(UpdateMem updateMem){
        myPageMapper.regGuard(updateMem);
    }

    // 등록자 등록
    public void regRegister(UpdateMem updateMem){
        myPageMapper.regRegister(updateMem);
    }

    // 아파트 이름 중복검사
    public int checkResidence(String res_name){
        return myPageMapper.checkResidence(res_name);
    }

    // 거주지 등록
    public void regResidence(Residence residence){
        myPageMapper.regResidence(residence);
    }

    // 차량 등록
    public void regCar(RegCar regCar){
        myPageMapper.regCar(regCar);
    }

    // 차량 불러오기
    public ArrayList<MemCarList> memCarList(String email){
        return myPageMapper.memCarList(email);
    }

    // 차 불러오기
    public ArrayList<RegCar> loadMyCar(String email){
        return myPageMapper.loadMyCar(email);
    }

    // 유저가 저장한 주차장 즐겨찾기 목록 불러오기
    public ArrayList<UserBookmark> userHpBookmark(String email){
        return myPageMapper.userHpBookmark(email);
    }

    // 즐겨찾기 주차장 목록 총 갯수 - 페이징
    public int userHpBookmarkCnt(UserCriteria userCriteria){
        return myPageMapper.userHpBookmarkCnt(userCriteria);
    }

    // 유저가 저장한 충전소 즐겨찾기 목록 불러오기
    public ArrayList<UserBookmark> userSCSBookmark(String email){
        return myPageMapper.userSCSBookmark(email);
    }

    // 즐겨찾기 충전소 목록 총 갯수 - 페이징
    public int userSCSBookmarkCnt(UserCriteria userCriteria){
        return myPageMapper.userSCSBookmarkCnt(userCriteria);
    }

    // 충전소 이용 내역
    public ArrayList<History> userSCSHistory(String email){
        return myPageMapper.userSCSHistory(email);
    }

    // 충전소 이용 내역 총 갯수 - 페이징
    public int userSCSHistoryCnt(UserCriteria userCriteria){
        return myPageMapper.userSCSHistoryCnt(userCriteria);
    }

    // 주차장 이용 내역
    public ArrayList<History> userHPHistory(String email){
        return myPageMapper.userHPHistory(email);
    }

    // 주차장 이용 내역 리스트 - 페이징
    public ArrayList<History> userHPHistoryList(UserCriteria userCriteria){
        System.out.println("myPageService userHPHistoryList userCriteria : " + userCriteria.toString());
        return myPageMapper.userHPHistoryList(userCriteria);
    }

    // 주차장 이용 내역 총 갯수 - 페이징
    public int userHPHistoryCnt(UserCriteria userCriteria){
        return myPageMapper.userHPHistoryCnt(userCriteria);
    }

    // 충전소 이용 내역 날짜 검색
    public ArrayList<History> scsHistorySearchList(History history){
        return myPageMapper.scsHistorySearchList(history);
    }

    // 충전소 이용 내역 날짜 검색 총 갯수 - 페이징
    public int scsHistorySearchListCnt(UserCriteria userCriteria){
        return myPageMapper.scsHistorySearchListCnt(userCriteria);
    }

    // 주차장 이용 내역 날짜 검색
    public ArrayList<History> hpHistorySearchList(History history){
        return myPageMapper.hpHistorySearchList(history);
    }

    // 주차장 이용 내역 날짜 검색 총 갯수 - 페이징
    public int hpHistorySearchListCnt(UserCriteria userCriteria){
        return myPageMapper.hpHistorySearchListCnt(userCriteria);
    }
}
