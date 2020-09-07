-- PAY 조회
select SCS_NAME, count(*) from SCS_PAY
group by SCS_NAME;

-- SCS 조회
select SCS_NAME, SCS_PIC from SCS;

-- 주거지
select * from RES;

/*
    1. PAY 조회를 먼저 시도
    2. 
*/