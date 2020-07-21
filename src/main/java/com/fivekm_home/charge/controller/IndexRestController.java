package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.Join;
import com.fivekm_home.charge.domain.USER.Login;
import com.fivekm_home.charge.domain.USER.LoginCheck;
import com.fivekm_home.charge.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class IndexRestController {
    @Autowired
    MemService memService;

    @PostMapping("/rest/join")
    public void join(Join join){
        memService.join(join);
    }

    @PostMapping("/rest/checkId")
    public int checkId(LoginCheck loginCheck){
        System.out.println("아이디 중복체크: "+loginCheck.toString());
        return memService.checkId(loginCheck);
    }

    @PostMapping("/rest/login")
    public Object login(Login login, HttpSession httpSession){
        SessionUser user = new SessionUser(memService.login(login));
        System.out.println("sessionUser.getName() : " + user.getName());
        System.out.println("sessionUser.getEmail() : " + user.getEmail());
        System.out.println("sessionUser.getRole() : " + user.getRole());
        httpSession.setAttribute("user", user);
        if(httpSession.getAttribute("user") != null){
            return "로그인 성공";
        }else{
            return "로그인 실패";
        }
    }
}
