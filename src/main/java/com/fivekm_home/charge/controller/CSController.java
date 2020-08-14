package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.CS.CS_pay;
import com.fivekm_home.charge.domain.HP.HP_chkBookmark;
import com.fivekm_home.charge.domain.HP.HP_pay;
import com.fivekm_home.charge.service.CSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chargingStation/*")
public class CSController {
    @Autowired
    CSService csService;

    @GetMapping("/chargingRegister")
    public String chargingRegister(Model model, HttpSession httpSession){
        return "/CS/csRegister";
    }

    @GetMapping("/chargingSearch")
    public String chargingSearch(Model model, HttpSession httpSession){
        model.addAttribute("searchList", csService.csSearchDataList());
        return "/CS/csSearch";
    }

    @GetMapping("/chargingHistory")
    public String chargingHistory(Model model, HttpSession httpSession){
        return "/CS/csHistory";
    }

    // 주차장 예약 페이지
    @GetMapping("/csBook/{chargeName}+{email}")
    public String csBookPage(@PathVariable String chargeName,
                             @PathVariable String email, Model model, HttpSession httpSession){
        // 로그인 되어있는지 검사부터 할께용
        if(httpSession.getAttribute("user") != null){
            System.out.println("주차장 이름 : " + chargeName + "    사용자 이메일 : " + email);
            model.addAttribute("charge", csService.csBookPage(chargeName));

            return "/CS/csBook";
        } else {
            return "/index/login";
        }

    }

}
