package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.service.HPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("book", hpService.hpBook());
        return "/HP/hpSearch";
    }

    @GetMapping("/happyParkingHistory") // 데이터 없음
    public String hpHistory(Model model){
        return "/HP/hpHistory";
    }


}
