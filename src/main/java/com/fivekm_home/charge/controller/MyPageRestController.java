package com.fivekm_home.charge.controller;

import com.fivekm_home.charge.domain.USER.MemberEdit;
import com.fivekm_home.charge.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageRestController {
    @Autowired
    MyPageService myPageService;


}
