package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.domain.USER.*;
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
    public String scsHistory(@PathVariable String email, Model model, HttpSession httpSession,
                             UserCriteria userCriteria, @RequestParam(defaultValue = "1") int page){
        System.out.println("criteria : " + userCriteria.toString());
        if(httpSession.getAttribute("user")!=null){
            UserPagination userPagination = new UserPagination(myPageService.userSCSHistoryListCnt(userCriteria), page);
            userCriteria.setPage(page);
            userCriteria.setEmail(email);
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            MemberEdit memberEdit = new MemberEdit(sessionUser);
            System.out.println("pagination : " + userPagination.toString());
            System.out.println("criteria : " + userCriteria.toString());
            model.addAttribute("pagination", userPagination);
            model.addAttribute("mem", memberEdit);
            model.addAttribute("scsHistory", myPageService.userSCSHistoryList(userCriteria));
            return "/myPage/scsHistory";
        }else {
            System.out.println("MyPageController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }
    }

    @GetMapping("/hpHistory/{email}")
    public String hpHistory(@PathVariable String email, Model model, HttpSession httpSession,
                            UserCriteria userCriteria, @RequestParam(defaultValue = "1") int page){
        System.out.println("criteria : " + userCriteria.toString());
        if(httpSession.getAttribute("user")!=null){
            UserPagination userPagination = new UserPagination(myPageService.userHPHistoryListCnt(userCriteria), page);
            userCriteria.setPage(page);
            userCriteria.setEmail(email);
            System.out.println("pagination : " + userPagination.toString());
            System.out.println("criteria : " + userCriteria.toString());
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
    public String scsHistorySearchList(@PathVariable String email, Model model, HttpSession httpSession,
                                       UserCriteria userCriteria, @RequestParam(defaultValue = "1") int page){
        if(httpSession.getAttribute("user")!=null){
            UserPagination userPagination = new UserPagination(myPageService.scsHistorySearchListCnt(userCriteria), page);
            userCriteria.setEmail(email);
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            MemberEdit memberEdit = new MemberEdit(sessionUser);
            model.addAttribute("mem", memberEdit);
            model.addAttribute("pagination", userPagination);
            System.out.println("email : " + email + "    return : " + myPageService.scsHistorySearchList(userCriteria));
            model.addAttribute("scsSearchHistory", myPageService.scsHistorySearchList(userCriteria));
            return "/myPage/scsHistorySearch";
        }else {
            System.out.println("MyPageController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }
    }

    // 주차장 이용 내역 날짜 검색
    @PostMapping("/hpHistorySearch/{email}")
    public String hpHistorySearchList(@PathVariable String email, Model model, HttpSession httpSession,
                                      UserCriteria userCriteria, @RequestParam(defaultValue = "1") int page){
        if(httpSession.getAttribute("user")!=null){
            UserPagination userPagination = new UserPagination(myPageService.hpHistorySearchListCnt(userCriteria), page);
            userCriteria.setEmail(email);
            SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
            MemberEdit memberEdit = new MemberEdit(sessionUser);
            model.addAttribute("mem", memberEdit);
            model.addAttribute("pagination", userPagination);
            model.addAttribute("hpSearchHistory", myPageService.hpHistorySearchList(userCriteria));
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
    public String SCSBookmark(@PathVariable String email, Model model, UserHistoryCriteria userHistoryCriteria,
                                @RequestParam(defaultValue = "1") int page){
        UserHistoryPagination userPagination = new UserHistoryPagination(myPageService.userSCSBookmarkCnt(userHistoryCriteria), page);
        userHistoryCriteria.setPage(page);
        System.out.println("pagination : " + userPagination.toString());
        System.out.println("criteria : " + userHistoryCriteria.toString());
        System.out.println("userSCSBookmark return : " + myPageService.userSCSBookmark(userHistoryCriteria));
        model.addAttribute("pagination", userPagination);
        model.addAttribute("scs", myPageService.userSCSBookmark(userHistoryCriteria));
        return "/myPage/scsBookmark";
    }

    // 회원 주차장 즐겨찾기 목록 불러오기
    @GetMapping("/hpBookmark/{email}")
    public String hpBookmark(@PathVariable String email, Model model, UserHistoryCriteria userHistoryCriteria,
                             @RequestParam(defaultValue = "1") int page){
        UserHistoryPagination userPagination = new UserHistoryPagination(myPageService.userHpBookmarkCnt(userHistoryCriteria), page);
        userHistoryCriteria.setPage(page);
        System.out.println("pagination : " + userPagination.toString());
        System.out.println("criteria : " + userHistoryCriteria.toString());
        System.out.println("userHpBookmark return : " + myPageService.userHpBookmark(userHistoryCriteria));
        model.addAttribute("pagination", userPagination);
        model.addAttribute("hp", myPageService.userHpBookmark(userHistoryCriteria));
        return "/myPage/hpBookmark";
    }
}
