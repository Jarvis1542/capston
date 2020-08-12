-- 충전소 예약
CREATE TABLE CS_BOOK (
    BOOKID      varchar2(100),  /* 예약 ID - SEQ, PK */
    BOOK_DATE    timestamp,     /* 예약 날짜 */
    START_TIME   timestamp,     /* 예약 시작 시간 */
    END_TIME     timestamp,     /* 예약 종료 시간 */
    STATE    varchar2(1) default 'N',   /* 예약 상태 */
    EMAIL       varchar2(100),  /* 사용자 이메일 - FK(MEMBER) */
    CHARGENAME varchar2(300),   /* 충전소 이름 - FK(CS) */
    constraint CS_BOOK_ID_PK primary key (BOOKID),
    constraint CS_BOOK_EMAIL_FK foreign key (EMAIL) references MEMBER (EMAIL),
    constraint CS_BOOK_CHARGENAME_FK foreign key (CHARGENAME) references CS (CHARGENAME)
        on delete cascade
);

-- 충전소 결제
CREATE TABLE CS_PAY (

);

-- 충전소 즐겨찾기
CREATE TABLE CS_BOOKMARK (

);