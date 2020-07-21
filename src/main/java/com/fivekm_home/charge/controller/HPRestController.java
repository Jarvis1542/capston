package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.HP.HP_register;
import com.fivekm_home.charge.service.HPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HPRestController {
    @Autowired
    HPService hpService;

    @PostMapping("/rest/happyParkingRegister")
    public void insertHappyParking(HP_register hp_register){
        hpService.hpReg(hp_register);
    }
}
