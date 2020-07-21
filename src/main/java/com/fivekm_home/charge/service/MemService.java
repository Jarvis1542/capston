package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.USER.Join;
import com.fivekm_home.charge.domain.USER.Login;
import com.fivekm_home.charge.domain.USER.LoginCheck;
import com.fivekm_home.charge.domain.USER.MemberList;
import com.fivekm_home.charge.mapper.MemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MemService {
    @Autowired(required=false)
    MemMapper memMapper;

    // 회원리스트
    public ArrayList<MemberList> memberList(){
        return memMapper.memberList();
    }

    //회원가입
    public void join(Join join){
        memMapper.join(join);
    }

    // 아이디 체크
    public int checkId(LoginCheck loginCheck) {
        return memMapper.checkId(loginCheck);
    }

    // 로그인
    public Login login(Login login){
        System.out.println("로그인 정보 : " + login.toString());
        return memMapper.login(login);
    }
}
