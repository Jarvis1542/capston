package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.Email;
import com.fivekm_home.charge.service.HPService;
import com.fivekm_home.charge.service.MailService;
import com.fivekm_home.charge.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class
IndexController {
    @Autowired
    MemService memService;
    @Autowired
    HPService hpService;
    @Autowired
    MailService mailService;

    @GetMapping("/")
    public String index() {
        return "/index/index";
    }

    @GetMapping("/index/join")
    public String join() {
        return "/index/join";
    }

    @GetMapping("/index/login")
    public String login(){
        return "/index/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }

    // 부트스트랩
    @GetMapping("/about")
    public String about(){
        return "/templetSample/about";
    }
    @GetMapping("/apply")
    public String apply(){
        return "/templetSample/apply";
    }
    @GetMapping("/blog")
    public String blog(){
        return "/templetSample/blog";
    }
    @GetMapping("/contact")
    public String contact(){
        return "/templetSample/contact";
    }
    @GetMapping("/elements")
    public String elements(){
        return "/templetSample/elements";
    }
    @GetMapping("/faq")
    public String faq(){
        return "/templetSample/faq";
    }
    @GetMapping("/loan")
    public String loan(){
        return "/templetSample/loan";
    }
    @GetMapping("/single-blog")
    public String single_blog(){
        return "/templetSample/single-blog";
    }
    @GetMapping("/asdfasdf")
    public String asdfasdf(){
        return "/index/asdfasdf";
    }
    @GetMapping("/main")
    public String mainn(){
        return "/templetSample/main";
    }
}
