package com.fivekm_home.charge.controller;

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
    public String Graph() {
        return "/otherService/graph";
    }
}
