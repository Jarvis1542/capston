package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.config.auth.dto.SessionUser;
import com.fivekm_home.charge.service.QBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/otherService")
public class OtherServiceController {
    @Autowired
    QBService qbService;

    @GetMapping("/parkingMap")
    public String ParkingMap() {
        return "/otherService/parkingMap";
    }

    @GetMapping("/EZCharging")
    public String EZCharging() {
        return "/otherService/EZCharging";
    }

    @GetMapping("/QnA")
    public String QnA(Model model, HttpSession httpSession) {
        model.addAttribute("boardList",qbService.qboardlist());
        return "/otherService/QnA";
    }

    @GetMapping("/select/{bno}")
    public String select(@PathVariable long bno, Model model, HttpSession httpSession){
        model.addAttribute("select", qbService.select(bno));
        qbService.noCount(bno);
        return "/otherService/select";
    }

    @GetMapping("/FAQ")
    public String bestQnA() {
        return "/otherService/FAQ";
    }

    @GetMapping("/QnAWrite")
    public String QnAWrite() {
        return "/otherService/QnAWrite";
    }
}
