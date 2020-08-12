package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.service.CSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}
