package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_requestEtc;
import com.fivekm_home.charge.service.CSService;
import com.fivekm_home.charge.service.HPService;
import com.fivekm_home.charge.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/*")
public class AdminRestController {
    @Autowired
    private HPService hpService;

    @Autowired
    private CSService csService;


    @PutMapping("/updateParkingChk")
    public void updateParkingChk(@RequestParam String parkingName){
        System.out.println("parkingName : " + parkingName);
        hpService.updateParkingChk(parkingName);
    }

    @PutMapping("/updateChargingChk")
    public void updateChargingChk(@RequestParam String chargeName){
        System.out.println("csName : " + chargeName);
        csService.updateChargingChk(chargeName);
    }
}