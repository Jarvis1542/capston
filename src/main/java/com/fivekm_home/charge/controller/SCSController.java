package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_bookmark;
import com.fivekm_home.charge.domain.SCS.SCS_bookmark;
import com.fivekm_home.charge.service.SCSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chargingStation/*")
public class SCSController {
    @Autowired
    SCSService scsService;

    @GetMapping("/scsRegister")
    public String chargingRegister(){
        return "/SCS/scsRegister";
    }

    @GetMapping("/scsSearch")
    public String chargingSearch(Model model){
        model.addAttribute("searchList", scsService.scsSearchDataList());
        return "/SCS/scsSearch";
    }

    @GetMapping("/scsHistory")
    public String chargingHistory(){
        return "/SCS/scsHistory";
    }

    // 주차장 예약 페이지
    @GetMapping("/scsBook/{scs_name}+{email}")
    public String csBookPage(@PathVariable String scs_name,  @PathVariable String email,
                             Model model, HttpSession httpSession){
        // 로그인 되어있는지 검사부터 할께용
        if(httpSession.getAttribute("user") != null){
            System.out.println("주차장 이름 : " + scs_name + "    사용자 이메일 : " + email);
            model.addAttribute("charge", scsService.scsBookPage(scs_name));

            // 북마크 검사
            SCS_bookmark scs_bookmark = new SCS_bookmark();
            scs_bookmark.setEmail(email);
            scs_bookmark.setScs_name(scs_name);
            System.out.println("checkBookmark return : " + scsService.checkBookmark(scs_bookmark));
            model.addAttribute("checkBookmark", scsService.checkBookmark(scs_bookmark));

            return "/SCS/scsBook";
        } else {
            System.out.println("CSController : 로그인되어 있지 않아 로그인 페이지로 요청했습니다.");
            return "/index/login";
        }
    }
}
