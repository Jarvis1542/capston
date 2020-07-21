package com.fivekm_home.charge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage")
public class MyPageController {

    @GetMapping("/memberEdit")
    public String memberEdit(){

        return "/myPage/memberEdit";
    }

    @GetMapping("/bookmark")
    public String bookmark(){

        return "/myPage/bookmark";
    }

    @GetMapping("/history")
    public String history(){

        return "/myPage/history";
    }

    @GetMapping("/residence")
    public String residence(){

        return "/myPage/residence";
    }
}
