package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_chkBookmark;
import com.fivekm_home.charge.service.HPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/happyParking")
public class HPController {
    @Autowired
    HPService hpService;


    @GetMapping("/happyParkingRegister")
    public String hpRegister(){
        return "/HP/hpRegister";
    }

    @GetMapping("/happyParkingSearch")
    public String happyParkingSearch(Model model){
        model.addAttribute("searchList", hpService.hpSearchDataList());
        return "/HP/hpSearch";
    }

    @GetMapping("/happyParkingHistory") // 데이터 없음
    public String hpHistory(Model model){
        return "/HP/hpHistory";
    }

    // 주차장 예약 페이지
    @GetMapping("/happyParkingBook/{parkingName}+{email}")
    public String hpBookPage(@PathVariable String parkingName,
            @PathVariable String email, Model model, HttpSession httpSession){

        // 로그인 되어있는지 검사부터 할께용
        if(httpSession.getAttribute("user") != null){
            System.out.println("주차장 이름 : " + parkingName + "    사용자 이메일 : " + email);
            model.addAttribute("parking", hpService.hpBookPage(parkingName));

            // 북마크 검사
            HP_chkBookmark hp_chkBookmark = new HP_chkBookmark();
            hp_chkBookmark.setEmail(email);
            hp_chkBookmark.setParkingName(parkingName);
            System.out.println("checkBookmark return : " + hpService.checkBookmark(hp_chkBookmark));
            model.addAttribute("checkBookmark", hpService.checkBookmark(hp_chkBookmark));

            return "/HP/hpBook";
        } else {
            System.out.println("HPController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }

    }


}
