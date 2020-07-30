package com.fivekm_home.charge.mapper;



import com.fivekm_home.charge.domain.USER.*;

import java.util.ArrayList;

public interface MemMapper {
    ArrayList<MemberList> memberList();
    void join(Join join);
    int checkId(LoginCheck loginCheck);
    SearchPassword searchPassword(SearchPassword searchPassword);
    SearchId searchId(SearchId searchId);
    Login login(Login login);
    MemberEdit2 MemberEdit(MemberEdit memberEdit);
    void kakaoJoin(KakaoJoin kakaoJoin);
    ArrayList<KakaoLogin> kakaoLoginCheck(KakaoLogin kakaoLogin) throws Exception;
    void updateGuard(UpdateMem updateMem);
    void updateRegister(UpdateMem updateMem);
}
