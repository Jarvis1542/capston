package com.fivekm_home.charge.service;

import com.fivekm_home.charge.domain.OS.*;
import com.fivekm_home.charge.mapper.QBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QBService {
    @Autowired(required = false)
    QBMapper qbMapper;
    
    //QnA게시판 글작성
    public void qbwrite(QB_write qb_write) {
        qbMapper.qbwrite(qb_write);
    }
    
    //게시판 글 클릭
    public QBSelect select(long bno){
        return qbMapper.select(bno);
    }

    //조회수
    public void noCount(long bno) {qbMapper.noCount(bno);}

    //게시글삭제
    public void delete(QBDelete qbdelete) {qbMapper.delete(qbdelete);}

    //게시판 리스트 뽑는곳
    public ArrayList<QBoardList> qboardlist() { return qbMapper.qboardList();}
}


