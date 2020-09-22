package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.PAGING.Criteria;
import com.fivekm_home.charge.domain.USER.*;

import java.util.ArrayList;

public interface MemMapper {
    ArrayList<MemberList> memberList(Criteria criteria); // 회원 리스트
    int memberListCnt(); // 회원 리스트 총 갯수 - 페이징
    void join(Join join);
    int checkId(LoginCheck loginCheck);
    SearchPassword searchPassword(SearchPassword searchPassword);
    SearchId searchId(SearchId searchId);
    Login login(Login login);
    Kakao kakaoLogin(String email);
    MemberEdit2 MemberEdit(MemberEdit memberEdit);
    void kakaoJoin(Kakao kakao);
    ArrayList<Kakao> kakaoLoginChk(String email);
    void updateGuard(UpdateMem updateMem);
    void updateRegister(UpdateMem updateMem);
    void updateNormal(UpdateMem updateMem);
}
