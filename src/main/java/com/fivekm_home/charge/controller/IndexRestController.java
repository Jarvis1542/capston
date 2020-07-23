package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.Email;
import com.fivekm_home.charge.domain.USER.Join;
import com.fivekm_home.charge.domain.USER.Login;
import com.fivekm_home.charge.domain.USER.LoginCheck;
import com.fivekm_home.charge.service.MailService;
import com.fivekm_home.charge.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class IndexRestController {
    @Autowired
    MemService memService;
    @Autowired
    MailService mailService;

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

    @PostMapping("/service/mail/*")
    @ResponseBody
    public void emailConfirm(Email email)throws Exception{
        System.out.println("전달 받은 이메일 : " + email.getUserId());
        mailService.sendSimpleMessage(email.getUserId());
    }
    @PostMapping("/verifyCode")
    @ResponseBody
    public int verifyCode(Email email) {
        int result = 0;
        System.out.println("code : "+email.getEmailAuthText());
        System.out.println("code match : "+ MailService.ePw.equals(email.getEmailAuthText()));
        if(MailService.ePw.equals(email.getEmailAuthText())) {
            result = 1;
            System.out.println("인증 결과 : " + result);
            System.out.println("ver(result) : " + ver(result));
            ver(result);
            return result;
        }else{
            result = 0;
            System.out.println("인증 결과 : " + result);
            System.out.println("ver(result) : " + ver(result));
            ver(result);
            return result;
        }
    }
    public static int ver(int ver){
        int result = 0;
        if(ver==1){
            return 1;
        }else{
            return 0;
        }
    }
}
