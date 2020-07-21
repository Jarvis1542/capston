package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.HP.HP_book;
import com.fivekm_home.charge.domain.HP.HP_register;
import com.fivekm_home.charge.domain.HP.HP_request;
import com.fivekm_home.charge.domain.HP.HP_requestList;

import java.util.ArrayList;

public interface HPMapper {
    void hpReg(HP_register hp_register);
    HP_request hpRequest(String parkingName);
    ArrayList<HP_requestList> hpRequestList();
    HP_book hpBook();
}
