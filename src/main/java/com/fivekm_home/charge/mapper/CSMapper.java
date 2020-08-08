package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.CS.*;

import java.util.ArrayList;

public interface CSMapper {
    void csReg(CS_register cs_register);
    ArrayList<CS_requestList> csRequestList();
}

