package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.DATE.*;
import com.fivekm_home.charge.domain.OTHER.MembersDate;
import com.fivekm_home.charge.domain.OTHER.PaycountSCS;
import com.fivekm_home.charge.domain.SALES.SalesDAO;
import com.fivekm_home.charge.service.DateService;
import com.fivekm_home.charge.service.OtherService;
import com.fivekm_home.charge.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class OtherRestController {
    @Autowired
    OtherService otherService;

    @Autowired
    DateService dateService;

    @Autowired
    SalesService salesService;

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

    @PostMapping("/rest/Sales")
    public ArrayList<SalesDAO> SalesController() {
        System.out.println("컨트롤러 CCC : " + salesService.SalesService());
        return salesService.SalesService();
    }

    @PostMapping("/rest/Sales2")
    public ArrayList<SalesDAO> Sales2Controller() {
        System.out.println("컨트롤러 DDD : " + salesService.Sales2Service());
        return salesService.Sales2Service();
    }

    @GetMapping("/rest/ranking")
    public ArrayList<PaycountSCS> ranking() {
        System.out.println("AAA는?" + otherService.ranking());
        return otherService.ranking();
    }
}
