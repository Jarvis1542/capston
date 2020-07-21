package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.USER.Join;
import com.fivekm_home.charge.domain.USER.Login;
import com.fivekm_home.charge.domain.USER.LoginCheck;
import com.fivekm_home.charge.domain.USER.MemberList;

import java.util.ArrayList;

public interface MemMapper {
    ArrayList<MemberList> memberList();
    void join(Join join);
    int checkId(LoginCheck loginCheck);
    Login login(Login login);
}
