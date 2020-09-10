package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.OTHER.MembersDate;
import com.fivekm_home.charge.domain.OTHER.PaycountHP;
import com.fivekm_home.charge.domain.OTHER.PaycountSCS;
import com.fivekm_home.charge.mapper.OtherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OtherService {
    @Autowired(required = false)
    OtherMapper otherMapper;

    public ArrayList<MembersDate> MembersDate(){
        return otherMapper.membersDate();
    }

    public ArrayList<PaycountSCS> ranking(){
        return otherMapper.ranking();
    }

    public ArrayList<PaycountHP> rankingHP(){
        return otherMapper.rankingHP();
    }

}
