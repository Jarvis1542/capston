package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.CS.CS_register;
import com.fivekm_home.charge.domain.HP.HP_reg;
import com.fivekm_home.charge.service.CSService;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class CSRestController {

    @Autowired
    CSService csService;
    @Autowired
    StorageService storageService;

    // 충전소 등록
    @PostMapping("/rest/csReg")
    public void hpReg(@RequestPart("upload") MultipartFile upload, // 주차장 사진
                      @RequestPart("upload2") MultipartFile upload2, // 아파트 내부 단지 지도(사진)
                      CS_register cs_register){
        storageService.store(upload);
        storageService.store(upload2);
        cs_register.setChargePic("/img/upload/"+upload.getOriginalFilename());
        cs_register.setAptMap("/img/upload/"+upload2.getOriginalFilename());
        System.out.println("data : "+ cs_register.toString());
        csService.csReg(cs_register);
    }
}
