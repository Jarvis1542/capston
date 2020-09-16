package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.RES.Residence;
import com.fivekm_home.charge.domain.USER.*;
import com.fivekm_home.charge.service.MemService;
import com.fivekm_home.charge.service.MyPageService;
import com.fivekm_home.charge.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public int edit(@RequestPart(value = "upload", required = false)MultipartFile upload,    // 경비 교육 이수증
                     @RequestPart(value = "upload2", required = false)MultipartFile upload2,  // 아파트 대표 증명 사진
                     UpdateMem updateMem){
        System.out.println("updateMem : " + updateMem.toString());

        // 일반
        if(updateMem.getMem_role()==null){
            memService.updateNormal(updateMem);
            return 3;
        }else {
            // 경비
            if(updateMem.getMem_role().equals("mp")){
                System.out.println("경비 : " + updateMem + "    파일 : " + upload.getOriginalFilename());
                storageService.store(upload);
                updateMem.setMp_lic("/img/upload/"+upload.getOriginalFilename());
                System.out.println("파일 저장 통과 후 : "  + updateMem.toString());
                myPageService.regGuard(updateMem);
                memService.updateGuard(updateMem);
                return 1;

                // 등록자
            }else if(updateMem.getMem_role().equals("reg")){
                System.out.println("등록자 : " + updateMem + "    파일 : " + upload.getOriginalFilename());
                storageService.store(upload2);
                updateMem.setReg_lic("/img/upload/"+upload2.getOriginalFilename());
                myPageService.regRegister(updateMem);
                memService.updateRegister(updateMem);
                return 2;
            }
        }
        return 0;
    } // end of edit

    @PostMapping("/rest/regCar")
    public void regCar(RegCar regCar){
        System.out.println("regCar : " + regCar.toString());
        myPageService.regCar(regCar);
    }

    // 차 불러오기
    @PostMapping("/rest/loadMyCar")
    public ArrayList<RegCar> loadMyCar(@RequestParam("email") String email){
        System.out.println("email : " + email);
        System.out.println("loadMyCarList : " + myPageService.loadMyCar(email));
        return myPageService.loadMyCar(email);
    }

    @PostMapping("/rest/checkResidence")
    public int checkResidence(@RequestParam("res_name") String res_name){
        System.out.println("이름 : " + res_name);
        return myPageService.checkResidence(res_name);
    }

    @PostMapping("/rest/residence")
    public void regResidence(@RequestParam("totalData[]") List<String> str, Residence residence){
        if(str.size()<10){
            residence.setRes_name(str.get(0));
            residence.setPost_code(str.get(1));
            residence.setRoad_addr(str.get(2));
            residence.setJibun_addr(str.get(3));
            residence.setExtra_addr(str.get(4));
            residence.setDetail_addr(str.get(5));
            residence.setLat(str.get(6));
            residence.setLng(str.get(7));
            residence.setEmail(str.get(8));
            myPageService.regResidence(residence);
        }else if(str.size()<19){
            residence.setRes_name(str.get(0));
            residence.setPost_code(str.get(1));
            residence.setRoad_addr(str.get(2));
            residence.setJibun_addr(str.get(3));
            residence.setExtra_addr(str.get(4));
            residence.setDetail_addr(str.get(5));
            residence.setLat(str.get(6));
            residence.setLng(str.get(7));
            residence.setEmail(str.get(8));
            myPageService.regResidence(residence);
            residence.setRes_name(str.get(9));
            residence.setPost_code(str.get(10));
            residence.setRoad_addr(str.get(11));
            residence.setJibun_addr(str.get(12));
            residence.setExtra_addr(str.get(13));
            residence.setDetail_addr(str.get(14));
            residence.setLat(str.get(15));
            residence.setLng(str.get(16));
            residence.setEmail(str.get(17));
            myPageService.regResidence(residence);
        }
    }// end of regResidence

    @GetMapping("/rest/scsHistory")
    public ArrayList<SCSHistory> scsHistorySearchList(SCSHistory scsHistory){
        return myPageService.userSCSHistory(scsHistory.getEmail());
    }
} // end of MyPageRestController
