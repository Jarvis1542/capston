package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.EditPassword;
import com.fivekm_home.charge.domain.USER.MemberEdit;
import com.fivekm_home.charge.domain.USER.MemberEdit2;
import com.fivekm_home.charge.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
    @Autowired
    MyPageService myPageService;

    @GetMapping("/memberEdit")
    public String memberEdit(Model model, HttpSession httpSession){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        MemberEdit memberEdit = new MemberEdit(sessionUser);
        model.addAttribute("memberEdit", myPageService.memberEdit(memberEdit));
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
