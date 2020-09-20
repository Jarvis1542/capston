package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.History;
import com.fivekm_home.charge.domain.USER.MemberEdit;
import com.fivekm_home.charge.domain.USER.UserCriteria;
import com.fivekm_home.charge.domain.USER.UserPagination;
import com.fivekm_home.charge.service.MyPageService;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.Request;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/scsHistory/{email}")
    public String scsHistory(@PathVariable String email, Model model, HttpSession httpSession){
        if(httpSession.getAttribute("user")!=null){
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            MemberEdit memberEdit = new MemberEdit(sessionUser);
            model.addAttribute("mem", memberEdit);
            model.addAttribute("scsHistory", myPageService.userSCSHistory(email));
            return "/myPage/scsHistory";
        }else {
            System.out.println("MyPageController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }
    }

    @GetMapping("/hpHistory/{email}")
    public String hpHistory(@PathVariable String email, Model model, HttpSession httpSession, UserCriteria userCriteria,
                            @RequestParam(defaultValue = "1") int page){
        if(httpSession.getAttribute("user")!=null){
            UserPagination userPagination = new UserPagination(myPageService.userHPHistoryCnt(userCriteria), page);
            userCriteria.setPage(page);
            userCriteria.setEmail(email);
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            MemberEdit memberEdit = new MemberEdit(sessionUser);
            model.addAttribute("pagination", userPagination);
            model.addAttribute("mem", memberEdit);
            model.addAttribute("hpHistory", myPageService.userHPHistoryList(userCriteria));
            return "/myPage/hpHistory";
        }else {
            System.out.println("MyPageController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }
    }

    // 충전소 이용 내역 날짜 검색
    @PostMapping("/scsHistorySearch/{email}")
    public String scsHistorySearchList(@PathVariable String email, Model model, HttpSession httpSession, History history){
        if(httpSession.getAttribute("user")!=null){
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            MemberEdit memberEdit = new MemberEdit(sessionUser);
            model.addAttribute("mem", memberEdit);
            model.addAttribute("scsSearchHistory", myPageService.scsHistorySearchList(history));
            return "/myPage/hpHistorySearch";
        }else {
            System.out.println("MyPageController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }
    }

    // 주차장 이용 내역 날짜 검색
    @PostMapping("/hpHistorySearch/{email}")
    public String hpHistorySearchList(@PathVariable String email, Model model, HttpSession httpSession, History history){
        if(httpSession.getAttribute("user")!=null){
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            MemberEdit memberEdit = new MemberEdit(sessionUser);
            model.addAttribute("mem", memberEdit);
            model.addAttribute("hpSearchHistory", myPageService.hpHistorySearchList(history));
            return "/myPage/hpHistorySearch";
        }else {
            System.out.println("MyPageController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }
    }

    @GetMapping("/residence")
    public String residence(){
        return "/myPage/residence";
    }

    // 회원 충전소 즐겨찾기 목록 불러오기
    @GetMapping("/scsBookmark/{email}")
    public String SCSBookmark(@PathVariable String email, Model model, UserCriteria userCriteria,
                                @RequestParam(defaultValue = "1") int page){
//        UserPagination userPagination = new UserPagination(myPageService.);
//        userCriteria.setPage(page);
//        model.addAttribute("pagination", userPagination); // 충전소 즐겨찾기 목록 하단 페이징
        System.out.println("email : " + email);
        System.out.println("userSCSBookmark return : " + myPageService.userSCSBookmark(email));
        if(myPageService.userSCSBookmark(email).equals(null)){
            model.addAttribute("scs", "조회된 데이터가 없습니다.");
        }else{
            model.addAttribute("scs", myPageService.userSCSBookmark(email));
        }
        return "/myPage/SCSBookmark";
    }

    // 회원 주차장 즐겨찾기 목록 불러오기
    @GetMapping("/hpBookmark/{email}")
    public String hpBookmark(@PathVariable String email, Model model, UserCriteria userCriteria,
                             @RequestParam(defaultValue = "1") int page){
//        UserPagination userPagination = new UserPagination(myPageService.);
        userCriteria.setPage(page);

        System.out.println("email : " + email);
        System.out.println("userHpBookmark return : " + myPageService.userHpBookmark(email));
        if(myPageService.userHpBookmark(email).equals(null)){
            model.addAttribute("hp", "조회된 데이터가 없습니다.");
        }else{
            model.addAttribute("hp", myPageService.userHpBookmark(email));
        }
        return "/myPage/hpBookmark";
    }
}
