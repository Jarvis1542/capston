package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.service.HPService;
import com.fivekm_home.charge.service.SCSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/*")
public class AdminRestController {
    @Autowired
    private HPService hpService;

    @Autowired
    private SCSService scsService;


    @PutMapping("/updateParkingChk")
    public void updateParkingChk(@RequestParam String hp_name){
        System.out.println("parkingName : " + hp_name);
        hpService.updateParkingChk(hp_name);
    }

    @PutMapping("/updateChargingChk")
    public void updateChargingChk(@RequestParam String scs_name){
        System.out.println("csName : " + scs_name);
        scsService.updateChargingChk(scs_name);
    }

}