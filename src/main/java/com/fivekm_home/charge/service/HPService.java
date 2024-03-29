package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.HP.*;
import com.fivekm_home.charge.domain.PAGING.Criteria;
import com.fivekm_home.charge.domain.SCS.SCS_pay;
import com.fivekm_home.charge.mapper.HPMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Service
public class HPService<T> {
    @Autowired(required = false)
    private HPMapper hpMapper;

    // 거주지 불러오기
    public ArrayList<HP_loadRes> loadResidence(String email) {
        return hpMapper.loadResidence(email);
    }

    // 주차장 등록
    public void hpReg(HP_reg hp_reg) {
        hpMapper.hpReg(hp_reg);
    }

    // 관리자에게 주차장 요청
    public HP_request hpRequest(String hp_name) {
        return hpMapper.hpRequest(hp_name);
    }

    // 주차장 요청 리스트 총 갯수 - 페이징
    public int hpRequestListCnt(){
        return hpMapper.hpRequestListCnt();
    }

    // 주차장 요청 리스트
    public ArrayList<HP_requestList> hpRequestList(Criteria criteria) {
        return hpMapper.hpRequestList(criteria);
    }

    // 주차장 예약 페이지
    public HP_bookPage hpBookPage(String hp_name) {
        return hpMapper.hpBookPage(hp_name);
    }

    // 주차장 승인
    public void updateParkingChk(String hp_name) {
        hpMapper.updateParkingChk(hp_name);
    }

    // 지도에 마크를 찍을 데이터 불러오기
    public ArrayList<HP_search> hpSearchDataList() {
        return hpMapper.hpSearchDataList();
    }

    // 예약버튼확인
    public int checkHPBookBtn(String email, String hp_name){
        return hpMapper.checkHPBookBtn(email, hp_name);
    }

    // 주차장 자리 변화 불러오기
    public ArrayList<HP_cnPlList> hpPlaceList() {
        return hpMapper.hpPlaceList();
    }

    // 지도에 보낼 데이터 total
//    public JSONObject hpTotalList(){
//        ArrayList<HP_search> se = hpMapper.hpSearchDataList();
//        JSONObject data = new JSONObject();
//        JSONArray total = new JSONArray();
//        JSONObject sObject = new JSONObject();
//        JsonArray hpSearch = new JsonArray();
//        JSONObject hp_cn_pl = new JSONObject();
//
//        data.put("hsList",se);
//        data.put("hpSearch", total);
//        return data;
//    }

    // 주차장 예약
    public void hpBook(HP_book hp_book) {
        hpMapper.hpBook(hp_book);
    }

    // 주차장 예약 체크 N -> y
    public void updateHPBookCHk(HP_pay hp_pay){
        hpMapper.updateHPBookCHk(hp_pay);
    }

    // 충전소 hp_chk = y 카운터 + 1
    public void updateHPCnt(){
        hpMapper.updateHPCnt();
    }

    public int checkHPChk(HP_pay hp_pay){
        return hpMapper.checkHPChk(hp_pay);
    }

    public void hpPayCnt(){
        hpMapper.hpPayCnt();
    }

    public int checkHPBookCnt(){
        return hpMapper.checkHPBookCnt();
    }

    // book_id fk 넣기
    public void hpPayBookIdUpdate(HP_pay hp_pay){
        hpMapper.hpPayBookIdUpdate(hp_pay);
    }

    // 주차장 결제
    public void hpPay(HP_pay hp_pay) {
        hpMapper.hpPay(hp_pay);
    }

    // 주차장 예약 페이지에서 사용자가 즐겨찾기를 판단
    public int checkBookmark(HP_bookmark hp_bookmark) {
        return hpMapper.checkBookmark(hp_bookmark);
    }

    // 주차장 즐겨찾기 추가
    public void addHpBookmark(HP_bookmark hp_bookmark) {
        hpMapper.addHpBookmark(hp_bookmark);
    }

    // 주차장 즐겨찾기 삭제
    public void deleteHpBookmark(HP_bookmark hp_bookmark) {
        hpMapper.deleteHpBookmark(hp_bookmark);
    }

    // 주차장 지도 검색
    public ArrayList<HP_mapSearch> hpMapSearch(String hp_name) { // 주차장 지도 검색
        System.out.println("서비스검정 : "+ hp_name);
        return hpMapper.hpMapSearch(hp_name);
    }

    // 예약 취소
    public void hpBookCancel(HP_book hp_book){
        hpMapper.hpBookCancel(hp_book);
    }
}