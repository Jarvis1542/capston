package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.SALES.SalesDAO;
import com.fivekm_home.charge.mapper.SalesIntfMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class SalesService {
    @Autowired(required = false)
    SalesIntfMapper salesIntfMapper;

    public ArrayList<SalesDAO> SalesService(){
        System.out.println("서비스");
        System.out.println("서비스 :  " + salesIntfMapper.SalesXML());
        return salesIntfMapper.SalesXML();
    }

    public ArrayList<SalesDAO> Sales2Service(){
        System.out.println("서비스");
        System.out.println("서비스 :  " + salesIntfMapper.Sales2XML());
        return salesIntfMapper.Sales2XML();
    }
}
