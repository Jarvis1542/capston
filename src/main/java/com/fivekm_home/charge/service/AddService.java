package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.ADDITION.GRAPHDTO;
import com.fivekm_home.charge.domain.USER.GraphList;
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
}
