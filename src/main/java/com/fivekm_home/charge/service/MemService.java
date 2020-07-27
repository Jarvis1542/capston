package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.USER.*;
import com.fivekm_home.charge.mapper.MemMapper;
import lombok.Data;
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

    // 카카오 회원가입
    public void kakaoJoin(KakaoJoin kakaoJoin){
        memMapper.kakaoJoin(kakaoJoin);
    }

    // 카카오 로그인 체크
    public ArrayList<KakaoLogin> kakaoLoginCheck(KakaoLogin kakaoLogin) throws Exception{
        return memMapper.kakaoLoginCheck(kakaoLogin);
    }

    //아이디찾기
    public SearchId searchId(SearchId searchId)
    {
       return memMapper.searchId(searchId);
    }

    //비밀번호찾기
    public SearchPassword searchPassword(SearchPassword searchPassword)
    {
        return memMapper.searchPassword(searchPassword);
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
