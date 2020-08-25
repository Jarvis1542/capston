package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.RES.Residence;
import com.fivekm_home.charge.domain.USER.EditPassword;
import com.fivekm_home.charge.domain.USER.UpdateMem;
import com.fivekm_home.charge.domain.USER.UserHpBookmark;
import com.fivekm_home.charge.domain.USER.UserSearchBookmark;

import java.util.ArrayList;

public interface MyPageMapper {
    int updatePassword(EditPassword editPassword);
    void regGuard(UpdateMem updateMem);
    void regRegister(UpdateMem updateMem);
    int checkResidence(String res_name);
    void regResidence(Residence residence);
    ArrayList<UserHpBookmark> userHpBookmark(String email); // 회원이 즐겨찾기한 주차장 목록 불러오기
}
