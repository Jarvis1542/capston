package com.fivekm_home.charge.controller;

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

    @GetMapping("/memberList")
    public String memberList(Model model){
        model.addAttribute("memberList", memService.memberList());
        return "/admin/memberList";
    }

    @GetMapping("/chargingRequest")
    public String csRequest(Model model){
        return "/admin/csRequest";
    }

    @GetMapping("/happyParkingRequest")
    public String hpRequest(Model model){
        return "/admin/hpRequest";
    }

    @GetMapping("/happyParkingRequestList")
    public String hpRequestList(Model model){
        model.addAttribute("requestList", hpService.hpRequestList());
        return "/admin/memberList";
    }

    @GetMapping("/requestHappyParking/{parkingName}")
    public String hpRequest(@PathVariable String parkingName, Model model){
        model.addAttribute("request", hpService.hpRequest(parkingName));
        return "/admin/hpRequest";
    }
}
