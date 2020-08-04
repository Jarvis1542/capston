package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.DATE.*;
import com.fivekm_home.charge.mapper.DataIntfMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class DateService {
    @Autowired(required = false)
    DataIntfMapper dataIntfMapper;

    public ArrayList<DateMonthDAO> DateMonthService(DateSendDAO DateSend){
        System.out.println("서비스");
        System.out.println("서비스 :  " + dataIntfMapper.DateMonthXML(DateSend));
        return dataIntfMapper.DateMonthXML(DateSend);
    }

    public ArrayList<DateWeekDAO> DateWeekService(DateSendDAO DateSend){
        System.out.println("서비스");
        System.out.println("서비스 :  " + dataIntfMapper.DateWeekXML(DateSend));
        return dataIntfMapper.DateWeekXML(DateSend);
    }

    public ArrayList<DateOneWeekDAO> DateOneWeekService(DateSendDAO DateSend) {
        System.out.println("서비스");
        System.out.println("서비스3 : " + dataIntfMapper.DateOneWeekXML(DateSend));
        return dataIntfMapper.DateOneWeekXML(DateSend);
    }

}
