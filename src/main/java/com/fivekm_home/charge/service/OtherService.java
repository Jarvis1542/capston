package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.OTHER.MembersMonths;
import com.fivekm_home.charge.mapper.OtherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class OtherService {
    @Autowired(required = false)
    OtherMapper otherMapper;

    public int membersYears(){
        return otherMapper.membersYears();
    }

    public ArrayList<MembersMonths> membersMonths(){
        return otherMapper.membersMonths();
    }
    
}
