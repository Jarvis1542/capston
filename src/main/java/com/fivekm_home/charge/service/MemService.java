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
    public void kakaoJoin(Kakao kakao){
        memMapper.kakaoJoin(kakao);
    }

    // 카카오 로그인 체크
    public ArrayList<Kakao> kakaoLoginChk(String email){
        return memMapper.kakaoLoginChk(email);
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
        return memMapper.login(login);
    }

    // 카카오 로그인
    public Kakao kakaoLogin(String email){
         return memMapper.kakaoLogin(email);
    }

    // 경비 회원으로 바꾸기
    public void updateGuard(UpdateMem updateMem){
        memMapper.updateGuard(updateMem);
    }

    // 등록자 회원으로 바꾸기
    public void updateRegister(UpdateMem updateMem){
        memMapper.updateRegister(updateMem);
    }

    // 일반 회원 정보 업데이트
    public void updateNormal(UpdateMem updateMem){
        System.out.println("UpdateMem Service : " + updateMem.toString());
        memMapper.updateNormal(updateMem);
    }
}
