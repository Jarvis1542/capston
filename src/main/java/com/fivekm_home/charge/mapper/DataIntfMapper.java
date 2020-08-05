package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.DATE.*;

import java.util.ArrayList;

public interface DataIntfMapper {
    ArrayList<DateMonthDAO> DateMonthXML(DateSendDAO DataSend);
    ArrayList<DateWeekDAO> DateWeekXML(DateSendDAO DataSend);
    ArrayList<DateOneWeekDAO> DateOneWeekXML(DateSendDAO DateSend);
}
