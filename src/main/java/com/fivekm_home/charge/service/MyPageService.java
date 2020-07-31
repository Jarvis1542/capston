package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.RES.Residence;
import com.fivekm_home.charge.domain.USER.EditPassword;
import com.fivekm_home.charge.domain.USER.MemberEdit;
import com.fivekm_home.charge.domain.USER.MemberEdit2;
import com.fivekm_home.charge.domain.USER.UpdateMem;
import com.fivekm_home.charge.mapper.MemMapper;
import com.fivekm_home.charge.mapper.MyPageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyPageService {
    @Autowired(required = false)
    MemMapper memMapper;
    @Autowired(required = false)
    MyPageMapper myPageMapper;

    public MemberEdit2 memberEdit(MemberEdit memberEdit){
        return memMapper.MemberEdit(memberEdit);
    }

    // 비밀번호 업데이트
    public int updatePassword(EditPassword editPassword){
        return myPageMapper.updatePassword(editPassword);
    }

    // 경비 등록
    public void regGuard(UpdateMem updateMem){
        myPageMapper.regGuard(updateMem);
    }

    // 등록자 등록
    public void regRegister(UpdateMem updateMem){
        myPageMapper.regRegister(updateMem);
    }

    // 아파트 이름 중복검사
    public int checkResidence(String resName){
        return myPageMapper.checkResidence(resName);
    }

    // 거주지 등록
    public void regResidence(Residence residence){
        myPageMapper.regResidence(residence);
    }
}
