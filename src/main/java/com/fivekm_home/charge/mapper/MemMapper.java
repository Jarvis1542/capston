package com.fivekm_home.charge.mapper;



import com.fivekm_home.charge.domain.USER.*;

import java.util.ArrayList;

public interface MemMapper {
    ArrayList<MemberList> memberList();
    void join(Join join);
    int checkId(LoginCheck loginCheck);
    SearchId searchId(SearchId searchId);
    Login login(Login login);
    MemberEdit MemberEdit(MemberEdit memberEdit);
}
