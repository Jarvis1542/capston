package com.fivekm_home.charge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/watchVehicle")
public class WatchVehicleController {
    @GetMapping("/watchVehicle")
    public String watchVehicle(){
        return "/watchVehicle/WV";
    }
}
