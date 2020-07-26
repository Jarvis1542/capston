package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.ADDITION.GRAPH;
import com.fivekm_home.charge.mapper.AddMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AddService {
    @Autowired(required = false)
    AddMapper addMapper;

    public ArrayList<GRAPH> graph(){
        return addMapper.Graph();
    }
}
