package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_bookmark;
import com.fivekm_home.charge.domain.HP.HP_loadRes;
import com.fivekm_home.charge.domain.HP.HP_reg;
import com.fivekm_home.charge.domain.HP.HP_search;
import com.fivekm_home.charge.service.HPService;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@RestController
public class HPRestController {
    @Autowired
    HPService hpService;
    @Autowired
    StorageService storageService;

    // 거주지 불러오기
    @PostMapping("/rest/loadResidence")
    public ArrayList<HP_loadRes> loadResidence(@RequestParam("email") String email){
        System.out.println("email : " + email);
        System.out.println("return 값 : " + hpService.loadResidence(email));
        return hpService.loadResidence(email);
    }

    // 주차장 등록
    @PostMapping("/rest/hpReg")
    public void hpReg(@RequestPart("upload") MultipartFile upload, // 주차장 사진
                      @RequestPart("upload2") MultipartFile upload2, // 아파트 내부 단지 지도(사진)
                      HP_reg hp_reg){
        storageService.store(upload);
        storageService.store(upload2);
        hp_reg.setParkingPic("/img/upload/"+upload.getOriginalFilename());
        hp_reg.setAptMap("/img/upload/"+upload2.getOriginalFilename());
        if(hp_reg.getParkingType().equals("기타")){
            hp_reg.setParkingType("기타 : " + hp_reg.getEtc());
        }
        System.out.println("data : "+ hp_reg.toString());
        hpService.hpReg(hp_reg);
    }

    // 지도에 마크를 찍을 데이터 불러오기
    @GetMapping("/rest/hpSearchData")
    public ArrayList<HP_search> hpSearchDataList(){
        System.out.println("return : " + hpService.hpSearchDataList());
        return hpService.hpSearchDataList();
    }

    // 주차장 즐겨찾기 추가
    @PostMapping("/rest/hpBookmark")
    public void addBookmark(HP_bookmark hp_bookmark) {
        System.out.println("data : " + hp_bookmark.toString());
        if(hp_bookmark.getImgSrc().equals("/img/bookmark.png")){
            hpService.addBookmark(hp_bookmark);
        }
        if(hp_bookmark.getImgSrc().equals("/img/bookmark2.png")){
            hpService.deleteBookmark(hp_bookmark);
        }
    }
}
