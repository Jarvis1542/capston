package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.ADDITION.WEEKLISTDTO;
import com.fivekm_home.charge.domain.USER.GraphList;
import com.fivekm_home.charge.domain.USER.SCSList;

import java.util.ArrayList;

public interface AddMapper {
    ArrayList<SCSList> scsList();
    ArrayList<GraphList> graphList();
    ArrayList<WEEKLISTDTO> weekList();
}
