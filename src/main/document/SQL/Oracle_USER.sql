/*  SQL CommandLine 기준
    아래 생성 쿼리의 최초 1234는 최초 Oracle 설치 비밀번호를 의미함.
    생성할 ID를 cap, 비밀번호를 1234라 가정하고 작성한 쿼리 예시*/

/* 유저 생성(sysdab 권한) */
conn sys as sysdba;
1234
create user test1 identified by 1234;
grant create trigger, connect, resource, dba to test1;
commit;



/* 유저 삭제 */
conn sys as sysdba;
1234
drop user cap cascade;
commit;
