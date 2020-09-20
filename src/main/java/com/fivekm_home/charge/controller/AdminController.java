package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_criteria;
import com.fivekm_home.charge.domain.HP.HP_pagination;
import com.fivekm_home.charge.domain.USER.UserCriteria;
import com.fivekm_home.charge.service.HPService;
import com.fivekm_home.charge.service.MemService;
import com.fivekm_home.charge.service.SCSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
    @Autowired(required = false)
    private MemService memService;
    @Autowired
    private HPService hpService;
    @Autowired
    private SCSService scsService;

    // 사용자 목록
    @GetMapping("/memberList")
    public String memberList(Model model){
        model.addAttribute("memberList", memService.memberList());
        return "admin/memberList";
    }
    /* CS - 충전소 영역 ---------------------------------------------- */
    // CS 충전소 요청 목록
    @GetMapping("/scsRequestList")
    public String scsRequestList(Model model){
        model.addAttribute("SCSRequestList", scsService.scsRequestList());
        return "/admin/scsRequestList";
    }

    // 해당 충전소 자세히 보기
    @GetMapping("/scsRequest/{scs_name}")
    public String scsRequest(@PathVariable String scs_name, Model model){
        System.out.println("return : " + scsService.scsRequest(scs_name));
        model.addAttribute("request", scsService.scsRequest(scs_name));
        return "/admin/scsRequest";
    }

    /* HP - 주차장 영역 --------------------------------------------- */
    // 주차장 요청 목록
    @GetMapping("/hpRequestList")
    public String hpRequestList(Model model, HP_criteria hp_criteria,
                                @RequestParam(defaultValue = "1") int page){
        HP_pagination hp_pagination = new HP_pagination(hpService.hpRequestListCnt(), page);
        hp_criteria.setPage(page);
        model.addAttribute("pagination", hp_pagination);
        model.addAttribute("hpRequestList", hpService.hpRequestList(hp_criteria));
        return "/admin/hpRequestList";
    }
    // 해당 주차장 자세히 보기
    @GetMapping("/hpRequest/{hp_name}")
    public String hpRequest(@PathVariable String hp_name, Model model){
        System.out.println("return : " + hpService.hpRequest(hp_name));
        model.addAttribute("request", hpService.hpRequest(hp_name));
        return "/admin/hpRequest";
    }

    // 매출현황 통계 그래프
    @GetMapping("/sales")
    public String salesStatus(){
        return "/admin/sales";
    }

}