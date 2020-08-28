package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.RES.Residence;
import com.fivekm_home.charge.domain.USER.*;
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
    public int checkResidence(String res_name){
        return myPageMapper.checkResidence(res_name);
    }

    // 거주지 등록
    public void regResidence(Residence residence){
        myPageMapper.regResidence(residence);
    }

    // 회원이 즐겨찾기한 주차장 목록 불러오기
    public ArrayList<UserHpBookmark> userHpBookmark(String email){
        return myPageMapper.userHpBookmark(email);
    }

    // 차량 등록
    public void regCar(RegCar regCar){
        myPageMapper.regCar(regCar);
    }

    // 차량 불러오기
    public ArrayList<MemCarList> memCarList(String email){
        return myPageMapper.memCarList(email);
    }
}
