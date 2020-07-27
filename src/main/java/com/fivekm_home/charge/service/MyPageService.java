package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.USER.MemberEdit;
import com.fivekm_home.charge.domain.USER.MemberEdit2;
import com.fivekm_home.charge.mapper.MemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPageService {
    @Autowired(required = false)
    MemMapper memMapper;

    public MemberEdit2 memberEdit(MemberEdit memberEdit){
        return memMapper.MemberEdit(memberEdit);
    }
}
