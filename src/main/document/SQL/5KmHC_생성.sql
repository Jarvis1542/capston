/* 테이블 생성 */
/* email PK 반영 */
create table member(
    email varchar2(255),
    password  varchar2(255) not null,
    name varchar2(255) not null,
    phone varchar2(255),
    picture varchar2(255),
    role varchar2(255) not null,
    created_date timestamp(6),
    modified_date timestamp(6),
    constraint member_email_pk primary key(email)
);

create table parking( /*주차장*/
    parkingIdx number, /*주차장코드*/
    parkingName varchar2(50) NULL, /*주차장이름*/
    parkingType varchar2(50) NOT NULL, /*주차장타입*/
    min30Fee number NULL, /*30분요금*/
    addMin10Fee   number NULL, /*10분추가요금*/
    place number NULL, /*면적(주차장칸수)*/
    sample4_postcode varchar2 (10), /*우편번호*/
    sample4_roadAddress varchar2(300), /*도로명주소*/
    sample4_jibunAddress varchar2(300), /*지번주소*/
    sample4_detailAddress varchar2(300), /*상세주소*/
    sample4_extraAddress varchar2(300), /*참고항목*/
    lat number null, /*위도*/
    lng number null, /*경도*/
    dayTime varchar2(30), /*평일시간*/
    weeTime varchar2(30), /*주말시간*/
    holTime varchar2(30), /*공휴일시간*/
    aptMap varchar2(1000) NULL, /*아파트단지지도-파일등록*/
    parkingPic varchar2(1000) NULL, /*주차장사진-파일등록*/
    email varchar2(255) not null,
    parkingDate   date NULL, /*등록날짜*/
    constraint parking_parkingIdx_pk primary key(parkingIdx),
    constraint parking_email_fk foreign key(email) references member(email));

create table CS(
    chargeName varchar2(50) NULL,
    operation varchar2(100) NULL,
    chargeSpeed varchar2(50) NULL,
    postcode varchar2(100) NULL,
    roadAddress varchar2(100) NULL,
    detailAddress varchar2(100) NULL,
    extraAddress varchar2(100) NULL,
    image1 varchar2(300),
    image2 varchar2(300),
    cable varchar2(50) NULL,
    chargeType varchar2(50) NULL
);

/* 등록자 */
create table register(
    email           varchar2(100), /*등록자 (pk, fk)*/
    registerLicence varchar2(2000), /*아파트 대표 증명 사진*/
    accountNum      varchar2(200), /*계좌 번호*/
    bankName        varchar2(200), /*은행 이름*/
    regDate         date, /*등록자 등록 일자*/
    constraint register_email_pk primary key (email),
    constraint register_email_fk foreign key (email) references member (email)
        on delete cascade
);

/* 거주지 */
create table residence(
    resName       varchar2(300), /*거주지 이름*/
    postCode      varchar2(10), /*우편 번호*/
    roadAddress   varchar2(300), /*도로명 주소*/
    jibunAddress  varchar2(300), /*지번 주소*/
    detailAddress varchar2(300), /*상세 주소*/
    extraAddress  varchar2(300), /*참고 항목*/
    lat           number null, /*위도*/
    lng           number null, /*경도*/
    regDate       date, /*등록 날짜*/
    email         varchar2(100), /*등록자(fk)*/
    constraint residence_resName_pk primary key (resName),
    constraint residence_email_fk foreign key (email) references register(email)
    on delete cascade
);

/* 경비 */
create table guard(
    email varchar2(100), /* 경비 회원 이메일*/
    guardLicence    varchar2(2000), /*경비 교육 이수증*/
    guardCo    varchar2(100), /*관리업체 이름*/
    guardCoNum varchar2(20), /*관리업체 번호*/
    regDate    date, /*경비 등록 일자*/
    resName    varchar2(300), /*거주지이름 (fk)*/
    constraint guard_email_pk primary key (email),
    constraint gurad_resName_fk foreign key (resName) references residence (resName),
    constraint guard_email_fk foreign key (email) references member (email)
    on delete cascade
);

create table q_board(
                        bno number not null,
                        title varchar2(100) not null,
                        content varchar2(1000) not null,
                        writer varchar2(50) not null,
                        regDate date,
                        noCount number,
                        noReco number,
                        mbo number,
                        constraint q_board_bno_pk primary key (bno)


);

/* 시퀀스 생성 */
CREATE SEQUENCE  parking_seq
    MINVALUE 1
    nomaxvalue
    INCREMENT BY 1
    START WITH 1
    CACHE 20
    NOORDER
    NOCYCLE;

CREATE SEQUENCE  q_board_seq
    INCREMENT BY 1
    START WITH 1;


/* 뷰 생성 */
create view member_view
as select * from member; /* member 뷰 작성*/

create view parking_view
as select * from parking; /* parking 뷰 작성*/

create view CS_view
as select * from CS;    /* CS 뷰 작성*/

create view guard_view /* 경비 뷰 작성*/
as select * from guard;

create view register_view /* 등록자 뷰 작성*/
as select * from register;

create view residence_view /* 거주지 뷰 작성*/
as select * from residence;

create view q_board_view
as select * from q_board;

/* 커밋 */
commit;