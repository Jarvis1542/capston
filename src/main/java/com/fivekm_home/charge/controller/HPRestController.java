package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_loadRes;
import com.fivekm_home.charge.domain.HP.HP_reg;
import com.fivekm_home.charge.service.HPService;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
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


}
