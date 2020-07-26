package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.ADDITION.GRAPHDTO;
import com.fivekm_home.charge.mapper.AddMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddService {
    @Autowired(required = false)
    AddMapper addMapper;

    public int graph(GRAPHDTO graphdto){
        System.out.println("그래프 서비스2" + addMapper.graphData(graphdto));
        return addMapper.graphData(graphdto);
    }
}
