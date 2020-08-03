package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.ADDITION.WEEKLISTDTO;
import com.fivekm_home.charge.domain.USER.GraphList;
import com.fivekm_home.charge.domain.USER.SCSList;
import com.fivekm_home.charge.mapper.AddMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AddService {
    @Autowired(required = false)
    AddMapper addMapper;

    public ArrayList<GraphList> graphList(){
        System.out.println("그래프 서비스" + addMapper.graphList());
        return addMapper.graphList();
    }
    public ArrayList<SCSList> scsList(){
        System.out.println("매출현황 서비스" + addMapper.scsList());
        return addMapper.scsList();
    }

    public ArrayList<WEEKLISTDTO> weekList(){
        System.out.println("위크 서비스 : " + addMapper.weekList());
        return addMapper.weekList();
    }
}
