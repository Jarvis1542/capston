package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.OS.*;

import java.util.ArrayList;

public interface QBMapper {
    void qbwrite(QB_write qb_write);
    QBSelect select(long bno);
    ArrayList<QBoardList> qboardList();
    void noCount(long bno);
    void delete(QBDelete qbdelete);

}
