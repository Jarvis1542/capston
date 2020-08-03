package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.OTHER.MembersMonths;
import com.fivekm_home.charge.service.OtherService;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class OtherRestController {
    @Autowired
    OtherService otherService;

    @PostMapping("/rest/MembersYears")
    public int membersYears(){
        return otherService.membersYears();
    }

    @PostMapping("/rest/MembersMonths")
    public ArrayList<MembersMonths> memebersMonths() {
        return otherService.membersMonths();
    }




}
