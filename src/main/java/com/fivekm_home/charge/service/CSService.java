package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.CS.CS_register;
import com.fivekm_home.charge.mapper.CSMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CSService {
    @Autowired(required = false)
    private CSMapper csMapper;

    public void csReg(CS_register cs_register) {
        csMapper.csReg(cs_register);
    }
}
