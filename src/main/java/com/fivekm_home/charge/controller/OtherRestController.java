package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.DATE.*;
import com.fivekm_home.charge.domain.OTHER.MembersDate;
import com.fivekm_home.charge.service.DateService;
import com.fivekm_home.charge.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class OtherRestController {
    @Autowired
    OtherService otherService;

    @Autowired
    DateService dateService;

    @PostMapping("/rest/MembersDate")
    public ArrayList<MembersDate> memebersMonths() {
        return otherService.MembersDate();
    }


    @PostMapping("/rest/DateMonth")
    public ArrayList<DateMonthDAO> DateMonthController(DateSendDAO DateSend) {
        System.out.println("컨트롤러");
        System.out.println("컨트롤러 :  " + dateService.DateMonthService(DateSend));
        return dateService.DateMonthService(DateSend);
    }

    @PostMapping("/rest/DateWeek")
    public ArrayList<DateWeekDAO> DateWeekController(DateSendDAO DateSend) {
        System.out.println("컨트롤러");
        System.out.println("컨트롤러 :  " + dateService.DateWeekService(DateSend));
        return dateService.DateWeekService(DateSend);
    }


    /*
    public int checkId(LoginCheck loginCheck) {
        return memMapper.checkId(loginCheck);
    }

     */
    @PostMapping("/rest/DateOneWeek")
    public ArrayList<DateOneWeekDAO> DateOneWeekController(DateSendDAO DateSend) {
        System.out.println("컨트롤러");
        System.out.println("컨트롤러3 :  " + dateService.DateOneWeekService(DateSend));
        return dateService.DateOneWeekService(DateSend);
    }






}
