package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_cnPlList;
import com.fivekm_home.charge.domain.SCS.*;
import com.fivekm_home.charge.service.SCSService;
import com.fivekm_home.charge.service.StorageService;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
public class SCSRestController {

    @Autowired
    SCSService scsService;
    @Autowired
    StorageService storageService;

    // 충전소 등록
    @PostMapping("/rest/scsReg")
    public void scsReg(@RequestPart("upload") MultipartFile upload, // 주차장 사진
                      @RequestPart("upload2") MultipartFile upload2, // 아파트 내부 단지 지도(사진)
                      SCS_reg scs_reg){
        storageService.store(upload);
        storageService.store(upload2);
        scs_reg.setScs_pic("/img/upload/"+upload.getOriginalFilename());
        scs_reg.setApt_map("/img/upload/"+upload2.getOriginalFilename());
        System.out.println("data : "+ scs_reg.toString());
        scsService.scsReg(scs_reg);
    }

    // 지도에 마크를 찍을 데이터 불러오기
    @GetMapping("/rest/scsSearchData")
    public ArrayList<SCS_search> scsSearchDataList(){
        System.out.println("return : " + scsService.scsSearchDataList());
        return scsService.scsSearchDataList();
    }

    // 지도에 충전소 자리 변화 불러오기
    @GetMapping("/rest/scsPlaceData")
    public ArrayList<SCS_chPlList> scsPlaceList(){
        System.out.println("return scsPlaceList CONTROLLER : " + scsService.scsPlaceList());
        return scsService.scsPlaceList();
    }

    // 충전소 예약
    @PostMapping("/rest/scsBook")
    public void scsBook(SCS_book scs_book){
        System.out.println("scs_book.toString : " + scs_book.toString());
        scsService.scsBook(scs_book);
    }

    // 충전소 결제
    @PostMapping("/rest/scsPay")
    public void scsPay(SCS_pay scs_pay){
        System.out.println("SCS_PAY " + scs_pay);
        scsService.scsPay(scs_pay);
    }

    // 충전소 즐겨찾기 추가
    @PostMapping("/rest/addSCSBookmark")
    public void addSCSBookmark(SCS_bookmark scs_bookmark) {
        System.out.println("data : " + scs_bookmark.toString());
        scsService.addSCSBookmark(scs_bookmark);
    }

    // 충전소 즐겨찾기 삭제
    @PostMapping("/rest/deleteSCSBookmark")
    public void deleteSCSBookmark(SCS_bookmark scs_bookmark) {
        System.out.println("data : " + scs_bookmark.toString());
        scsService.deleteSCSBookmark(scs_bookmark);
    }

    // 충전소 지도 검색
    @PostMapping("/rest/scsMapSearch")
    public ArrayList<SCS_mapSearch> scsMapSearch(String scs_name) {
        return scsService.scsMapSearch(scs_name);
    }
}
