package com.fivekm_home.charge.mapper;

import com.fivekm_home.charge.domain.OTHER.*;

import java.util.ArrayList;

public interface OtherMapper {
    int membersYears();
    ArrayList<MembersMonths> membersMonths();
    ArrayList<MembersDays> membersDays();
    ArrayList<MembersTerm> membersTerm();


}
