package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.RES.Residence;
import com.fivekm_home.charge.domain.USER.EditPassword;
import com.fivekm_home.charge.domain.USER.MemberEdit;
import com.fivekm_home.charge.domain.USER.UpdateMem;
import com.fivekm_home.charge.service.MemService;
import com.fivekm_home.charge.service.MyPageService;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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

    @PostMapping("/rest/checkResidence")
    public int checkResidence(@RequestParam("resName") String resName){
        System.out.println("이름 : " + resName);
        return myPageService.checkResidence(resName);
    }

    @PostMapping("/rest/residence")
    public void regResidence(@RequestParam("totalData[]") List<String> str, Residence residence){
        if(str.size()<10){
            residence.setResName(str.get(0));
            residence.setPostcode(str.get(1));
            residence.setRoadAddress(str.get(2));
            residence.setJibunAddress(str.get(3));
            residence.setExtraAddress(str.get(4));
            residence.setDetailAddress(str.get(5));
            residence.setLat(str.get(6));
            residence.setLng(str.get(7));
            residence.setEmail(str.get(8));
            myPageService.regResidence(residence);
        }else if(str.size()<19){
            residence.setResName(str.get(0));
            residence.setPostcode(str.get(1));
            residence.setRoadAddress(str.get(2));
            residence.setJibunAddress(str.get(3));
            residence.setExtraAddress(str.get(4));
            residence.setDetailAddress(str.get(5));
            residence.setLat(str.get(6));
            residence.setLng(str.get(7));
            residence.setEmail(str.get(8));
            myPageService.regResidence(residence);
            residence.setResName(str.get(9));
            residence.setPostcode(str.get(10));
            residence.setRoadAddress(str.get(11));
            residence.setJibunAddress(str.get(12));
            residence.setExtraAddress(str.get(13));
            residence.setDetailAddress(str.get(14));
            residence.setLat(str.get(15));
            residence.setLng(str.get(16));
            residence.setEmail(str.get(17));
            myPageService.regResidence(residence);
        }


    }
} // end of MyPageRestController
