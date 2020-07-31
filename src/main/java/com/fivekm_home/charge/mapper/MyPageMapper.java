package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.USER.EditPassword;
import com.fivekm_home.charge.domain.USER.UpdateMem;

public interface MyPageMapper {
    int updatePassword(EditPassword editPassword);
    void regGuard(UpdateMem updateMem);
    void regRegister(UpdateMem updateMem);
    int checkResidence(String resName);
}
