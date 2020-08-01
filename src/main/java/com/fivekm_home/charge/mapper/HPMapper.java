package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.HP.*;

import java.util.ArrayList;

public interface HPMapper {
    ArrayList<HP_loadRes> loadResidence(String email);
    void hpReg(HP_reg hp_reg);
    HP_request hpRequest(String parkingName);
    ArrayList<HP_requestList> hpRequestList();
    HP_book hpBook();
}
