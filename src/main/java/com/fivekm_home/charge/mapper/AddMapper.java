package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.ADDITION.WEEKLISTDTO;
import com.fivekm_home.charge.domain.USER.GraphList;

import java.util.ArrayList;

public interface AddMapper {
    ArrayList<GraphList> graphList();
    ArrayList<WEEKLISTDTO> weekList();
}