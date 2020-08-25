--------------- 트리거 생성 -----------------

--------------- 시퀀스 생성 -----------------
-- qna_board 시퀀스
CREATE SEQUENCE  qna_board_seq
    INCREMENT BY 1
    START WITH 1;

-- cs_pay 시퀀스
CREATE SEQUENCE scs_pay_seq
    start with 1
    INCREMENT by 1;

-- cs_book 시퀀스
CREATE SEQUENCE scs_book_seq
    START WITH 1
    INCREMENT BY 1;

-- hp_pay 시퀀스
create sequence hp_pay_seq
start with 1
increment by 1;

-- hp_book 시퀀스
create sequence hp_book_seq
start with 1
increment by 1;

-- entry_info 시퀀스
create sequence entry_info_seq
start with 1
increment by 1;
-- # 테이블 생성 쿼리(쿼리 순서대로 실행 권장) --------------------------------------------------------------
create table member(
    email varchar2(100),
    created_date timestamp,
    modified_date timestamp,
    name varchar2(30),
    password varchar2(100),
    phone varchar2(20),
    picture varchar2(1000),
    role varchar2(10) not null,
    primary key (email)
);

/* 차량 */
create table car(
    car_id varchar2(255),    /* EMAIL + SEQ.NUM = PK*/
    car_name varchar2(100),  /* 차량 이름(별칭) */
    car_model varchar2(255), /* 차량 모델 */
    scs_type varchar2(25),   /* 충전 방식 */
    email varchar2(100),    /* 사용자 이메일 - FK(MEMBER) */
    constraint car_car_id primary key (car_id)
);

/* 등록자 */
create table reg(
     email           varchar2(100), /*등록자 (pk, fk)*/
     reg_lic varchar2(2000), /*아파트 대표 증명 사진*/
     acc_num      varchar2(200), /*계좌 번호*/
     bank_name        varchar2(200), /*은행 이름*/
     reg_date         timestamp, /*등록자 등록 일자*/
     constraint reg_email_pk primary key (email),
     constraint reg_email_fk foreign key (email) references member(email)
         on delete cascade
);

/* 거주지 */
create table res(
    res_name       varchar2(300), /*거주지 이름*/
    post_code      varchar2(10), /*우편 번호*/
    road_addr   varchar2(300), /*도로명 주소*/
    jibun_addr  varchar2(300), /*지번 주소*/
    detail_addr varchar2(300), /*상세 주소*/
    extra_addr  varchar2(300), /*참고 항목*/
    lat           number null, /*위도*/
    lng           number null, /*경도*/
    reg_date       timestamp, /*등록 날짜*/
    email         varchar2(100), /*등록자(fk)*/
    constraint res_res_name_pk primary key (res_name),
    constraint res_email_fk foreign key (email) references reg(email)
      on delete cascade
);

/* 경비 */
create table mp /* management office 경비 줄인말 */
(
    email        varchar2(100), /* 경비 회원 이메일*/
    mp_lic varchar2(2000), /*경비 교육 이수증*/
    mp_co      varchar2(100), /*관리업체 이름*/
    mp_co_num   varchar2(20), /*관리업체 번호*/
    reg_date      timestamp, /*경비 등록 일자*/
    res_name      varchar2(300), /*거주지이름 (fk)*/
    constraint mp_email_pk primary key (email),
    constraint mp_res_name_fk foreign key (res_name) references res (res_name),
    constraint mp_email_fk foreign key (email) references member(email)
        on delete cascade
);

/* 카메라 */
create table entry_info
(
    entry_id number,
    en_car varchar2(10),
    en_time timestamp,
    ex_time timestamp,
    res_name varchar2(300),
    constraint entry_info_entry_id_pk primary key (entry_id),
    constraint entry_info_res_name_fk foreign key (res_name) references res(res_name)
    on delete cascade
);

/* 충전소 */
create table scs
(
    scs_name varchar2(50) NULL,       /* 충전소 이름 */
    oper_ins varchar2(100) NULL,       /* 운영 기관 */
    scs_speed varchar2(50) NULL,      /* 충전 속도 */
    scs_amount varchar2(50) NULL,     /* 충전기 수 */
    min30_fee varchar2(100) NULL,        /* 기본 요금 */
    addMin10_fee varchar2(100) NULL,     /* 추가 요금 */
    manage_time  varchar2(30),           /* 운영 시간 */
    scs_pic varchar2(2000),           /* 충전소 사진 */
    apt_map varchar2(2000),              /* 아파트 내부단지 지도 */
    cable varchar2(50) NULL,            /* 케이블 */
    scs_type varchar2(50) NULL,       /* 충전 타입 */
    reg_date timestamp,                  /* 충전소 등록 날짜 */
    scs_chk  varchar2(1) default 'N',/* 승인 여부 */
    res_name     varchar2(300),          /* 거주지 등록자 이름 */
    constraint scs_scs_name_pk primary key (scs_name),
    constraint scs_scs_chk_ck check (scs_chk in('Y', 'N')),
    constraint scs_res_name_fk foreign key (res_name) references res (res_name)
        on delete cascade
);

/* 충전소 예약 */
CREATE TABLE scs_book (
    book_id      varchar2(100),          /* 예약 ID - SEQ, PK */
    book_date    timestamp,             /* 예약 날짜 */
    start_date   timestamp,             /* 예약 시작 시간 */
    end_time     timestamp,             /* 예약 종료 시간 */
    state    varchar2(1) default 'N',   /* 예약 상태 */
    email       varchar2(100),          /* 사용자 이메일 - FK(MEMBER) */
    scs_name varchar2(300),           /* 충전소 이름 - FK(CS) */
    constraint scs_book_id_pk primary key (book_id),
    constraint scs_book_email_fk foreign key (email) references member (email),
    constraint scs_book_scs_name_fk foreign key (scs_name) references scs (scs_name)
        on delete cascade
);

/* 충전소 결제 */
CREATE TABLE scs_pay (
    pay_id varchar2(100),        /* 결제 ID - SEQ, PK */
    pay_way varchar2(20),     /* 결제 수단 */
    scs_name varchar2(10),    /* 충전소 이름 */
    price number,               /* 가격 */
    email varchar2(30),         /* 사용자 이메일 - FK(MEMBER) */
    phone varchar2(15),         /* 휴대폰 번호 */
    book_id varchar2(100),       /* 예약 ID - FK */
    pay_date timestamp,         /* 결제 날짜 */
    constraint scs_pay_pay_id_pk primary key (pay_id),
    constraint scs_pay_book_id_fk foreign key (book_id) references scs_book (book_id)
       on delete cascade
);

/* 충전소 즐겨찾기 */
CREATE TABLE scs_bookmark (
    email varchar2(100),        /* 이메일 - PK/FK(MEMBER) */
    scs_name varchar2(300),   /* 충전소 이름 - PK/FK(CS) */
    constraint scs_bookmark_pk primary key (email, scs_name), /* EMAIL+CHARGENAME = PK */
    constraint scs_bookmark_enauk_fk foreign key (email) references member (email),
    constraint scs_bookmark_scs_name_fk foreign key (scs_name) references scs(scs_name)
        on delete cascade
);

/* 주차장 */
create table hp(
    hp_name varchar2(300), /* 주차장 이름 */
    hp_type varchar2(30), /* 주차장 타입 */
    place       number, /* 주차장 칸 수 */
    min30_fee    number, /* 기본요금(30분당) */
    addMin10_fee number, /* 추가요금(10분당) */
    manage_time  varchar2(30), /* 운영 시간 */
    hp_pic  varchar2(2000), /* 주차장 사진 */
    apt_map      varchar2(2000), /* 아파트 단지 내부 지도(사진) */
    reg_date     timestamp, /* 주차장 등록 날짜 */
    hp_chk  varchar2(1) default 'N',
    res_name     varchar2(300), /* 거주지이름(fk) */
    constraint hp_hp_name_pk primary key (hp_name),
    constraint hp_hp_chk_ck check (hp_chk in('Y', 'N')),
    constraint hp_res_name_fk foreign key (res_name) references res (res_name)
        on delete cascade
);

/* 주차장 예약 */
create table hp_book
(
    book_id      varchar2(100), /* YYYY/MM/dd + parkingName + email */
    start_date   timestamp,
    end_date     timestamp,
    state    varchar2(1) default 'N',
    email       varchar2(100),
    hp_name varchar2(300),
    book_date    timestamp,
    constraint hp_book_book_id_pk primary key (book_id),
    constraint hp_book_email_fk foreign key (email) references member (email),
    constraint hp_book_hp_name_fk foreign key (hp_name) references hp (hp_name)
        on delete cascade
);

/* 주차장 결제 */
create table hp_pay(
   pay_id varchar2(100), /* YYYY/MM/dd + email */
   pay_way varchar2(20),
   name varchar2(10),
   price number,
   email varchar2(30),
   hp_name varchar2(300),
   phone varchar2(15),
   book_id varchar2(100), /* parkingBook (fk) */
   pay_date timestamp,
   constraint hp_pay_pay_id_pk primary key (pay_id),
   constraint hp_pay_book_id_fk foreign key (book_id) references hp_book(book_id)
       on delete cascade
);

/* 주차장 즐겨찾기 */
create table hp_bookmark(
    email varchar2(100),
    hp_name varchar2(300),
    constraint hp_bookmark_email_hp_name_pk primary key (email, hp_name),
    constraint hp_bookmark_email_fk foreign key (email) references member(email),
    constraint hp_bookmark_hp_name_fk foreign key (hp_name) references hp(hp_name)
        on delete cascade
);

/* QnA 게시판 테이블 작성 */
create table qna_board
(
    bno     number         not null,
    title   varchar2(100)  not null,
    content varchar2(1000) not null,
    writer  varchar2(50)   not null,
    reg_date date,
    no_count number,
    no_reco  number,
    mbo     number,
    constraint qna_board_bno_pk primary key (bno)
);
-- # 뷰 생성 쿼리 --------------------------------------------------------------
/* 멤버 */
create view member_view
as select * from member;
/* 차량 */
create view car_view
as select * from car;
/* 등록자 */
create view reg_view
as select * from reg;
/* 거주지 */
create view res_view
as select * from res;
/* 경비 */
create view mp_view
as select * from mp;
/* 카메라 */
create view entry_info_view
as select * from entry_info;
/* 충전소 */
create view scs_view
as select * from scs;
/* 충전소 예약*/
create view scs_book_view
as select * from scs_book;
/* 충전소 결제 */
create view scs_pay_view
as select * from scs_pay;
/* 충전소 즐겨찾기 */
create view scs_bookmark_view
as select * from scs_bookmark;
/* 주차장 */
create view hp_view
as select * from hp;
/* 주차장 예약 */
create view hp_book_view
as select * from hp_book;
/* 주차장 결제 */
create view hp_pay_view
as select * from hp_pay;
/* 주차장 즐겨찾기 */
create view hp_bookmark_view
as select * from hp_bookmark;
/* QnA 게시판 뷰 작성*/
create view qna_board_view
as select * from qna_board;
