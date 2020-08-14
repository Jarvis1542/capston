package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.CS.CS_book;
import com.fivekm_home.charge.domain.CS.CS_pay;
import com.fivekm_home.charge.domain.CS.CS_register;
import com.fivekm_home.charge.domain.CS.CS_search;
import com.fivekm_home.charge.domain.HP.HP_book;
import com.fivekm_home.charge.domain.HP.HP_reg;
import com.fivekm_home.charge.domain.HP.HP_search;
import com.fivekm_home.charge.service.CSService;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class CSRestController {

    @Autowired
    CSService csService;
    @Autowired
    StorageService storageService;

    // 충전소 등록
    @PostMapping("/rest/csReg")
    public void csReg(@RequestPart("upload") MultipartFile upload, // 주차장 사진
                      @RequestPart("upload2") MultipartFile upload2, // 아파트 내부 단지 지도(사진)
                      CS_register cs_register){
        storageService.store(upload);
        storageService.store(upload2);
        cs_register.setChargePic("/img/upload/"+upload.getOriginalFilename());
        cs_register.setAptMap("/img/upload/"+upload2.getOriginalFilename());
        System.out.println("data : "+ cs_register.toString());
        csService.csReg(cs_register);
    }

    // 지도에 마크를 찍을 데이터 불러오기
    @GetMapping("/rest/csSearchData")
    public ArrayList<CS_search> csSearchDataList(){
        System.out.println("return : " + csService.csSearchDataList());
        return csService.csSearchDataList();
    }

    // 충전소 결제
    @PostMapping("/rest/cspay")
    public void csPay(CS_pay cs_pay){
        System.out.println("CS_PAY " + cs_pay);
        csService.csPay(cs_pay);
    }

    // 충전소 예약
    @PostMapping("/rest/csbook")
    public void csBook(CS_book cs_book){
        System.out.println("cs_book.toString : " + cs_book.toString());
        csService.csBook(cs_book);
    }
}
