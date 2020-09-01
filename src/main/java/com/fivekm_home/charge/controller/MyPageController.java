package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.MemberEdit;
import com.fivekm_home.charge.domain.USER.RegCar;
import com.fivekm_home.charge.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
    @Autowired
    MyPageService myPageService;

    @GetMapping("/memberEdit")
    public String memberEdit(Model model, HttpSession httpSession){
        // 로그인 되어있는지 검사부터 할께용
        if(httpSession.getAttribute("user") != null){
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            MemberEdit memberEdit = new MemberEdit(sessionUser);
            System.out.println("memberEdit.toString() : " + memberEdit.toString());
            System.out.println("memberEdit return : "+ myPageService.memberEdit(memberEdit));
            model.addAttribute("memberEdit", myPageService.memberEdit(memberEdit));
            System.out.println("memCarList : " + myPageService.memCarList(memberEdit.getEmail()));
            model.addAttribute("memCarList", myPageService.memCarList(memberEdit.getEmail()));
            return "/myPage/memberEdit";
        } else {
            System.out.println("MyPageController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }

    }

    @GetMapping("/history")
    public String history(){
        return "/myPage/history";
    }

    @GetMapping("/residence")
    public String residence(){
        return "/myPage/residence";
    }

    // 회원 충전소 즐겨찾기 목록 불러오기
    @GetMapping("/SCSBookmark/{email}")
    public String SCSBookmark(@PathVariable String email, Model model){
        System.out.println("email : " + email);
        System.out.println("userSCSBookmark return : " + myPageService.userSCSBookmark(email));
        System.out.println("asdfasdf : " + myPageService.userHpBookmark(email).isEmpty());
        if(myPageService.userSCSBookmark(email).equals(null)){
            model.addAttribute("scs", "조회된 데이터가 없습니다.");
        }else{
            model.addAttribute("scs", myPageService.userSCSBookmark(email));
        }
        return "/myPage/SCSBookmark";
    }

    // 회원 주차장 즐겨찾기 목록 불러오기
    @GetMapping("/hpBookmark/{email}")
    public String hpBookmark(@PathVariable String email, Model model){
        System.out.println("email : " + email);
        System.out.println("userHpBookmark return : " + myPageService.userHpBookmark(email));
        System.out.println("asdfasdf : " + myPageService.userHpBookmark(email).isEmpty());
        if(myPageService.userHpBookmark(email).equals(null)){
            model.addAttribute("hp", "조회된 데이터가 없습니다.");
        }else{
            model.addAttribute("hp", myPageService.userHpBookmark(email));
        }
        return "/myPage/hpBookmark";
    }
}
