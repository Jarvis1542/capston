package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.USER.EditPassword;
import com.fivekm_home.charge.domain.USER.MemberEdit;
import com.fivekm_home.charge.domain.USER.UpdateMem;
import com.fivekm_home.charge.service.MemService;
import com.fivekm_home.charge.service.MyPageService;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MyPageRestController {
    @Autowired
    MyPageService myPageService;
    @Autowired
    StorageService storageService;
    @Autowired
    MemService memService;

    @PostMapping("/rest/updatePassword")
    public int updatePassword(EditPassword editPassword){
        return myPageService.updatePassword(editPassword);
    }

    @PostMapping("/rest/edit")
    public int edit(@RequestPart("upload")MultipartFile upload,    // 경비 교육 이수증
                     @RequestPart("upload2")MultipartFile upload2,  // 아파트 대표 증명 사진
                     UpdateMem updateMem){
        System.out.println("내용 : " + updateMem.toString() + "   파일 :"+ upload + "   파일2 :" + upload2);
        // 경비
        if(updateMem.getMemberRole().equals("guard")){
            System.out.println("경비 : " + updateMem + "    파일 : " + upload.getOriginalFilename());
            storageService.store(upload);
            updateMem.setGuardLicence("/img/upload/"+upload.getOriginalFilename());
            System.out.println("파일 저장 통과 후 : "  + updateMem.toString());
            myPageService.regGuard(updateMem);
            memService.updateGuard(updateMem);
            return 1;
        // 등록자
        }else if(updateMem.getMemberRole().equals("register")){
            System.out.println("등록자 : " + updateMem + "    파일 : " + upload.getOriginalFilename());
            storageService.store(upload2);
            updateMem.setRegisterLicence("/img/upload/"+upload2.getOriginalFilename());
            myPageService.regRegister(updateMem);
            memService.updateRegister(updateMem);
            return 2;
        // 둘 다 아닐 때
        }else{
            return 3;
        }
    } // end of edit


} // end of MyPageRestController
