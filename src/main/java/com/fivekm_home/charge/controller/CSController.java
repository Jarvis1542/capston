package com.fivekm_home.charge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chargingStation/*")
public class CSController {

    @GetMapping("/chargingRegister")
    public String chargingRegister(Model model, HttpSession httpSession){
        return "/CS/csRegister";
    }

    @GetMapping("/chargingSearch")
    public String chargingSearch(Model model, HttpSession httpSession){
        return "/CS/csSearch";
    }

    @GetMapping("/chargingHistory")
    public String chargingHistory(Model model, HttpSession httpSession){
        return "/CS/csHistory";
    }

}
