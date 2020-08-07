package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_requestEtc;
import com.fivekm_home.charge.service.HPService;
import com.fivekm_home.charge.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
    @Autowired(required = false)
    private MemService memService;

    @Autowired
    private HPService hpService;

    // 사용자 목록
    @GetMapping("/memberList")
    public String memberList(Model model){
        model.addAttribute("memberList", memService.memberList());
        return "admin/memberList";
    }

    // 주차장 요청 목록
    @GetMapping("/happyParkingRequestList")
    public String hpRequestList(Model model){
        model.addAttribute("requestList", hpService.hpRequestList());
        return "/admin/hpRequestList";
    }

    // 해당 주차장 자세히 보기
    @GetMapping("/requestHappyParking/{parkingName}")
    public String hpRequest(@PathVariable String parkingName, Model model){
        System.out.println("parkingName : " + parkingName);
        System.out.println("return : " + hpService.hpRequest(parkingName));
        HP_requestEtc hp_requestEtc = new HP_requestEtc();
        if(hpService.hpRequest(parkingName).getParkingType().substring(0,2).equals("기타")){
            hp_requestEtc.setEtc(hpService.hpRequest(parkingName).getParkingType());
            hp_requestEtc.setParkingType("기타");
            hp_requestEtc.setName(hpService.hpRequest(parkingName).getName());
            hp_requestEtc.setParkingName(hpService.hpRequest(parkingName).getParkingName());
            hp_requestEtc.setPlace(hpService.hpRequest(parkingName).getPlace());
            hp_requestEtc.setMin30Fee(hpService.hpRequest(parkingName).getMin30Fee());
            hp_requestEtc.setAddMin10Fee(hpService.hpRequest(parkingName).getAddMin10Fee());
            hp_requestEtc.setManageTime(hpService.hpRequest(parkingName).getManageTime());
            hp_requestEtc.setPostcode(hpService.hpRequest(parkingName).getPostcode());
            hp_requestEtc.setRoadAddress(hpService.hpRequest(parkingName).getRoadAddress());
            hp_requestEtc.setJibunAddress(hpService.hpRequest(parkingName).getJibunAddress());
            hp_requestEtc.setDetailAddress(hpService.hpRequest(parkingName).getDetailAddress());
            hp_requestEtc.setExtraAddress(hpService.hpRequest(parkingName).getExtraAddress());
            hp_requestEtc.setAptMap(hpService.hpRequest(parkingName).getAptMap());
            hp_requestEtc.setParkingPic(hpService.hpRequest(parkingName).getParkingPic());
            hp_requestEtc.setLat(hpService.hpRequest(parkingName).getLat());
            hp_requestEtc.setLng(hpService.hpRequest(parkingName).getLng());
            System.out.println("수정 후 : " + hp_requestEtc);
            model.addAttribute("request", hp_requestEtc);
            return "/admin/hpRequest";
        }
        System.out.println("수정 후 : " + hpService.hpRequest(parkingName));
        model.addAttribute("request", hpService.hpRequest(parkingName));
        return "/admin/hpRequest";
    }

    /* CS - 충전소 영역 */

    // CS 충전소 요청 목록
    @GetMapping("/csRequestList")
    public String csRequestList(Model model){
        return "/admin/csRequest";
    }
}