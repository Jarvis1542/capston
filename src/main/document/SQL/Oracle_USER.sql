/*  SQL CommandLine 기준
    아래 sysdba 로그인의 1234는 최초 Oracle 설치 비밀번호를 의미함. */

/* 유저 생성(sysdab 권한) */
conn sys as sysdba; -- sysdba 로그인
1234
create user cap identified by 1234; -- cap = username / 1234 = password
grant connect, resource, dba to cap;
commit; -- 커밋
conn cap/1234;  -- 로그인 테스트


/* 유저 삭제 */
conn sys as sysdba; -- sysdba 로그인
1234
drop user cap cascade; -- cap = username
commit; -- 커밋
