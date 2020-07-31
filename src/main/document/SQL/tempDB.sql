CREATE TABLE SCS(
    SCSNO NUMBER(255) PRIMARY KEY,  /* 충전소 번호 -PK */
    SCSNAME VARCHAR2(255),          /* 충전소 이름 */
    BASICPAY NUMBER(255),          /* 기본 요금(30분 기준) */
    OVERPAY NUMBER(255),        /* 추가 요금(10분 기준) */
    STATE VARCHAR2(10),             /* 충전소 상태 */
    DATE_REG DATE,                  /* 충전소 등록 날짜 */
    DATE_OPERSTART DATE,            /* 운영 시작 시간 */
    DATE_OPEREND DATE,              /* 운영 종료 시간 */
    DATE_BOOKSTART DATE,            /* 예약 시작 시간 */
    DATE_BOOKEND DATE,              /* 예약 종료 시간 */
    DATE_USEAGE DATE,               /* 사용 시간(예약시작-예약종료) */
    DATE_PAY DATE,                  /* 결제 날짜 */
    PRICE NUMBER(100),              /* 총 결제 금액(사용시간->기본+추가요금=합산) */
    PAYSTATE CHAR(1),               /* 결제 상태(Y/N) */
    PAYCOUNT NUMBER(100)            /* 결제 횟수(하루 기준) */
);
    /* 그래프 매출현황 구하는 로직
    DATE_BOOKEND - DATE_BOOKSTART = DATE_USEAGE (예약종료시간-예약시작시간=사용시간)
    이후 컨트롤러에서 JAVA 문법 활용으로...
    DATE_USEAGE -> 시간 분으로 표기했다는 가정하에 분으로 표기(ex:120분)
    int useTime = DB(DATE_USEAGE);
    useTime = useTime - 30
    while(0<=useTime){
        price += OVERPAY
        useTime-10;
        미완성!!!!!!!!!!!!!
    }
     */

/* 날짜 계산을 위한 임시 테이블 */
CREATE TABLE DATETEST(
    DATE1 DATE,
    DATE2 DATE
);