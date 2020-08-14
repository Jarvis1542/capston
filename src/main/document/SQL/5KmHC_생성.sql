-- # 시퀀스 생성 쿼리 --------------------------------------------------------------

/* QnA 게시판 시퀀스 작성*/
CREATE SEQUENCE  q_board_seq
    INCREMENT BY 1
    START WITH 1;

-- CS_PAY(PAYID) 시퀀스
CREATE SEQUENCE CS_PAY_SEQ
    start with 1
    INCREMENT by 1;

-- CS_BOOK(BOOKID) 시퀀스
CREATE SEQUENCE CS_BOOK_SEQ
    START WITH 1
    INCREMENT BY 1;

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

create table register(
     email           varchar2(100), /*등록자 (pk, fk)*/
     registerLicence varchar2(2000), /*아파트 대표 증명 사진*/
     accountNum      varchar2(200), /*계좌 번호*/
     bankName        varchar2(200), /*은행 이름*/
     regDate         timestamp, /*등록자 등록 일자*/
     constraint register_email_pk primary key (email),
     constraint register_email_fk foreign key (email) references member(email)
         on delete cascade
);

create table residence(
    resName       varchar2(300), /*거주지 이름*/
    postCode      varchar2(10), /*우편 번호*/
    roadAddress   varchar2(300), /*도로명 주소*/
    jibunAddress  varchar2(300), /*지번 주소*/
    detailAddress varchar2(300), /*상세 주소*/
    extraAddress  varchar2(300), /*참고 항목*/
    lat           number null, /*위도*/
    lng           number null, /*경도*/
    regDate       timestamp, /*등록 날짜*/
    email         varchar2(100), /*등록자(fk)*/
    constraint residence_resName_pk primary key (resName),
    constraint residence_email_fk foreign key (email) references register(email)
      on delete cascade
);

create table guard
(
    email        varchar2(100), /* 경비 회원 이메일*/
    guardLicence varchar2(2000), /*경비 교육 이수증*/
    guardCo      varchar2(100), /*관리업체 이름*/
    guardCoNum   varchar2(20), /*관리업체 번호*/
    regDate      timestamp, /*경비 등록 일자*/
    resName      varchar2(300), /*거주지이름 (fk)*/
    constraint guard_email_pk primary key (email),
    constraint gurad_resName_fk foreign key (resName) references residence (resName),
    constraint guard_email_fk foreign key (email) references member(email)
        on delete cascade
);

-- 충전소
create table CS
(
    chargeName varchar2(50) NULL,       /* 충전소 이름 */
    operation varchar2(100) NULL,       /* 운영 기관 */
    chargeSpeed varchar2(50) NULL,      /* 충전 속도 */
    chargeAmount varchar2(50) NULL,     /* 충전기 수 */
    min30Fee varchar2(100) NULL,        /* 기본 요금 */
    addMin10Fee varchar2(100) NULL,     /* 추가 요금 */
    manageTime  varchar2(30),           /* 운영 시간 */
    chargePic varchar2(2000),           /* 충전소 사진 */
    aptMap varchar2(2000),              /* 아파트 내부단지 지도 */
    cable varchar2(50) NULL,            /* 케이블 */
    chargeType varchar2(50) NULL,       /* 충전 타입 */
    regDate timestamp,                  /* 충전소 등록 날짜 */
    chargingChk  varchar2(1) default 'N',/* 승인 여부 */
    resName     varchar2(300),          /* 거주지 등록자 이름 */
    constraint CS_chargeName_pk primary key (chargeName),
    constraint CS_chargingChk_ck check (chargingChk in('Y', 'N')),
    constraint CS_resName_fk foreign key (resName) references residence (resName)
        on delete cascade
);

-- 충전소 예약
CREATE TABLE CS_BOOK (
    BOOKID      varchar2(100),          /* 예약 ID - SEQ, PK */
    BOOK_DATE    timestamp,             /* 예약 날짜 */
    START_TIME   timestamp,             /* 예약 시작 시간 */
    END_TIME     timestamp,             /* 예약 종료 시간 */
    STATE    varchar2(1) default 'N',   /* 예약 상태 */
    EMAIL       varchar2(100),          /* 사용자 이메일 - FK(MEMBER) */
    CHARGENAME varchar2(300),           /* 충전소 이름 - FK(CS) */
    constraint CS_BOOK_ID_PK primary key (BOOKID),
    constraint CS_BOOK_EMAIL_FK foreign key (EMAIL) references MEMBER (EMAIL),
    constraint CS_BOOK_CHARGENAME_FK foreign key (CHARGENAME) references CS (CHARGENAME)
        on delete cascade
);

-- 충전소 결제
CREATE TABLE CS_PAY (
    PAYID varchar2(100),        /* 결제 ID - SEQ, PK */
    PAYMETHOD varchar2(20),     /* 결제 수단 */
    CHARGENAME varchar2(10),    /* 충전소 이름 */
    PRICE number,               /* 가격 */
    EMAIL varchar2(30),         /* 사용자 이메일 - FK(MEMBER) */
    PHONE varchar2(15),         /* 휴대폰 번호 */
    BOOKID varchar2(100),       /* 예약 ID - FK */
    PAY_DATE timestamp,         /* 결제 날짜 */
    constraint CS_PAY_ID_PK primary key (PAYID),
    constraint CS_PAY_BOOKID_FK foreign key (BOOKID) references CS_BOOK(BOOKID)
       on delete cascade
);

-- 충전소 즐겨찾기
CREATE TABLE CS_BOOKMARK (
    EMAIL varchar2(100),        /* 이메일 - PK/FK(MEMBER) */
    CHARGENAME varchar2(300),   /* 충전소 이름 - PK/FK(CS) */
    constraint CS_BOOKMARK_PK primary key (EMAIL, CHARGENAME), /* EMAIL+CHARGENAME = PK */
    constraint CS_BOOKMARK_EMAIL_FK foreign key (EMAIL) references MEMBER(EMAIL),
    constraint CS_BOOKMARK_CHARGENAME_FK foreign key (CHARGENAME) references CS(CHARGENAME)
        on delete cascade
);


create table parking(
    parkingName varchar2(300), /* 주차장 이름 */
    parkingType varchar2(30), /* 주차장 타입 */
    place       number, /* 주차장 칸 수 */
    min30Fee    number, /* 기본요금(30분당) */
    addMin10Fee number, /* 추가요금(10분당) */
    manageTime  varchar2(30), /* 운영 시간 */
    parkingPic  varchar2(2000), /* 주차장 사진 */
    aptMap      varchar2(2000), /* 아파트 단지 내부 지도(사진) */
    regDate     timestamp, /* 주차장 등록 날짜 */
    parkingChk  varchar2(1) default 'N',
    resName     varchar2(300), /* 거주지이름(fk) */
    constraint parking_parkingName_pk primary key (parkingName),
    constraint parking_parkingChk_ck check (parkingChk in('Y', 'N')),
    constraint parking_resName_fk foreign key (resName) references residence (resName)
        on delete cascade
);

create table parkingBook
(
    bookId      varchar2(100), /* YYYY/MM/dd + parkingName + email */
    startDate   timestamp,
    endDate     timestamp,
    payState    varchar2(1) default 'N',
    email       varchar2(100),
    parkingName varchar2(300),
    bookDate    timestamp,
    constraint parkingBook_id_pk primary key (bookId),
    constraint parkingBook_email_fk foreign key (email) references member (email),
    constraint parkingBook_parkingName_fk foreign key (parkingName) references parking (parkingName)
        on delete cascade
);

create table parkingPay(
   payId varchar2(100), /* YYYY/MM/dd + email */
   payMethod varchar2(20),
   name varchar2(10),
   price number,
   email varchar2(30),
   parkingName varchar2(300),
   phone varchar2(15),
   bookId varchar2(100), /* parkingBook (fk) */
   payDate timestamp,
   constraint parkingPay_id_pk primary key (payId),
   constraint parkingPay_bookId_fk foreign key (bookId) references parkingBook(bookId)
       on delete cascade
);

create table parkingBookmark(
    email varchar2(100),
    parkingName varchar2(300),
    constraint pBookmark_email_parkingName_pk primary key (email, parkingName),
    constraint pBookmark_email_fk foreign key (email) references member(email),
    constraint pBookmark_parkingName_fk foreign key (parkingName) references parking(parkingName)
        on delete cascade
);

/* QnA 게시판 테이블 작성 */
create table q_board
(
    bno     number         not null,
    title   varchar2(100)  not null,
    content varchar2(1000) not null,
    writer  varchar2(50)   not null,
    regDate date,
    noCount number,
    noReco  number,
    mbo     number,
    constraint q_board_bno_pk primary key (bno)
);

/* 차량 */
create table CAR(
    CARID varchar2(255),    /* EMAIL + SEQ.NUM = PK*/
    CARNAME varchar2(100),  /* 차량 이름(별칭) */
    CARMODEL varchar2(255), /* 차량 모델 */
    CSTYPE varchar2(100),   /* 충전 방식 */
    EMAIL varchar2(100),    /* 사용자 이메일 - FK(MEMBER) */
    constraint CAR_ID_PK primary key (CARID)
);

-- # 뷰 생성 쿼리 --------------------------------------------------------------
drop table parkingPay;
/* 멤버 */
create view member_view
as select * from member;
/* 경비 */
create view guard_view
as select * from guard;
/* 등록자 */
create view register_view
as select * from register;
/* 거주지 */
create view residence_view
as select * from residence;
/* 충전소 */
create view CS_view
as select * from CS;
/* 충전소 즐겨찾기 */
create view CSBookmark_view
as select * from CS_BOOKMARK;
/* 주차장 */
create view parking_view
as select * from parking;
/* 주차장 즐겨찾기 */
create view parkingBookmark_view
as select * from parkingBookmark;
/* 차량 */
create view CAR_VIEW
as select * from CAR;

/* QnA 게시판 뷰 작성*/
create view q_board_view
as select * from q_board;

create view parkingBook_view
as select * from parkingBook;

create view parkingPay_view
as select * from parkingPay;

