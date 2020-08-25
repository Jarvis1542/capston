package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.*;
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
        hp_reg.setHp_pic("/img/upload/"+upload.getOriginalFilename());
        hp_reg.setApt_map("/img/upload/"+upload2.getOriginalFilename());
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
        if(hp_bookmark.getImg_src().equals("/img/bookmark.png")){
            hpService.addBookmark(hp_bookmark);
        }
        if(hp_bookmark.getImg_src().equals("/img/bookmark2.png")){
            hpService.deleteBookmark(hp_bookmark);
        }
    }

    // 주차장 예약
    @PostMapping("/rest/book")
    public void hpBook(HP_book hp_book){
        System.out.println("hp_book.toString : " + hp_book.toString());
        hpService.hpBook(hp_book);
    }

    // 주차장 결제
    @PostMapping("/rest/pay")
    public void hpPay(HP_pay hp_pay){
        System.out.println("HP_PAY " + hp_pay   );
        hpService.hpPay(hp_pay);
    }
}
