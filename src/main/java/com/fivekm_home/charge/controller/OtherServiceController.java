package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.mapper.AddMapper;
import com.fivekm_home.charge.service.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/otherService")
public class OtherServiceController {
    @Autowired
    AddService addService;

    @GetMapping("/parkingMap")
    public String ParkingMap() {
        return "/otherService/parkingMap";
    }

    @GetMapping("/EZCharging")
    public String EZCharging() {
        return "/otherService/EZCharging";
    }

    @GetMapping("/QnA")
    public String QnA() {
        return "/otherService/QnA";
    }

    @GetMapping("/FAQ")
    public String FAQ() {
        return "/otherService/FAQ";
    }

    @GetMapping("/graph")
    public String Graph(Model model) {
        model.addAttribute("graph", addService.graph());
        return "/otherService/graph";
    }


}
