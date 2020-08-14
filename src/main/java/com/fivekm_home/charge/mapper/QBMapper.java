package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.OS.*;

import java.util.ArrayList;

public interface QBMapper {
    void qbwrite(QB_write qb_write);
    void update(QBUpdate qbUpdate);
    QBSelect select(long bno);
    ArrayList<QBoardList> qboardList();
    ArrayList<QBReplyList> qbreplyList(long bno);
    void noCount(long bno);
    void noReco(QBreco qbreco);
    void delete(QBDelete qbdelete);
    void reply(QBReply qbReply);
}
