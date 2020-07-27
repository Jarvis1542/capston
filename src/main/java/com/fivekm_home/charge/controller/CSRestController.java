package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.CS.CS_register;
import com.fivekm_home.charge.service.CSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class CSRestController {

    @Autowired
    CSService csService;

    public static String uploadpath = "D:\\uploadTest\\";

    @PostMapping("/scs/rest/save")
    public void csRegister(CS_register cs_register, MultipartFile file1, MultipartFile file2){
        try{
            file1.transferTo(new File(uploadpath + file1.getOriginalFilename()));
            file2.transferTo(new File(uploadpath + file2.getOriginalFilename()));
            cs_register.setImage1(uploadpath + file1.getOriginalFilename());
            cs_register.setImage2(uploadpath + file2.getOriginalFilename());
            csService.csReg(cs_register);
        } catch (IllegalStateException | IOException e){
            e.printStackTrace();
        }
        System.out.println(cs_register.toString());
    }
}
