
/* 오라클 SQL Developer 시분초 나오게 하는 방법
환경설정 - 데이터베이스 - NLS - 날짜 형식 에서 RR/MM/DD HH24:MI:SS 로 변경 해 주면 된다. */

/* 충전소 정보 등록 테이블 (ex: A충전소, B충전소 ... ) */
CREATE TABLE SCS(
    SCSNO NUMBER(38) PRIMARY KEY,   /* 충전소 번호 -PK */
    NAME VARCHAR2(255),             /* 충전소 이름 */
    ADDRESS VARCHAR2(255),          /* 충전소 주소 */
    BASICPAY NUMBER(38),            /* 기본 요금(30분 기준) */
    OVERPAY NUMBER(38),             /* 추가 요금(10분 기준) */
    STATE VARCHAR2(10),             /* 충전소 상태 */
    DATE_REG DATE,                  /* 충전소 등록 날짜 */
    DATE_OPERSTART DATE,            /* 운영 시작 시간 */
    DATE_OPEREND DATE              /* 운영 종료 시간 */
);

/* 충전소 예약 및 결제 테이블 (for 고객) */
CREATE TABLE SCS_BOOK(
    DATE_BOOKSTART DATE,            /* 예약 시작 시간 */
    DATE_BOOKEND DATE,              /* 예약 종료 시간 */
    DATE_USEAGE DATE,               /* 사용 시간(예약시작-예약종료) */
    DATE_PAY DATE,                  /* 결제 날짜 */
    PRICE NUMBER(38),               /* 총 결제 금액(사용시간->기본+추가요금=합산) */
    PAYSTATE CHAR(1),               /* 결제 상태(Y/N) */
    PAYCOUNT NUMBER(38),            /* 결제 횟수(하루 기준) */
    SCSNO NUMBER(38),               /* 외래키 SCSNO */
    constraint SCSBOOK_SCSNO_FK foreign key(SCSNO) references SCS(SCSNO)   /* 제약조건 : SCS - SCSNO 외래키 */
);

    /* 그래프 매출현황 구하는 로직
    DATE_BOOKEND - DATE_BOOKSTART = DATE_USEAGE (예약종료시간-예약시작시간=사용시간)
    이후 컨트롤러에서 JAVA 문법 활용으로...
    DATE_USEAGE -> 시간 분으로 표기했다는 가정하에 분으로 표기(ex:120분)
    int useTime = DB(DATE_USEAGE);
    useTime = useTime - 30
    while(0<=useTime){
        price += OVERPAY(DB의 추가요금)
        useTime-10;
    }
    price의 결과물을 저장
    지정한 날짜에 따라 조회된 price 데이터들을 배열로 저장
    저장된 배열을 그래프 Data에 넣어 그래프로 출력!
     */

/* 날짜 계산을 위한 임시 테이블(ex: DATE2-DATE1) */
CREATE TABLE DATETEST(
    DATE1 DATE,
    DATE2 DATE
);

/* BasicPay + OverPay 계산 쿼리(테스트X) */
SELECT ((ROUND((DATE_BOOKEND-DATE_BOOKSTART)*24*60)-30)*OVERPAY)+BASICPAY FROM SCS;


