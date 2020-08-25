-------- 트리거 생성 예제 ---------
-- 1. 예제 계정 생성
conn sys as sysdba;
1234
create user test01 identified by test01;
grant create trigger, connect, resource, dba to test01;
commit;

-- 2. 예제 테이블 생성 (test01)
create table test01(
no number,
name varchar2(10)
);

-- 3. 트리거로 데이터가 입력될 테이블 생성 (test02)
create table test02 as select * from test01;

-- 4. 트리거 생성
create or replace trigger insert_test02
after insert on test01
for each row
declare
begin
    insert into test02 values(:new.no, :new.name);
end;
/

-- 5. test01 테이블에 데이터 입력
insert into test01 values(1, 'a');

-- 6. 테이블 조회
select * from test01;
select * from test02;

-- 7. 테이블 삭제
drop table test01;
drop table test02;

-- 8. 유저 삭제
drop user test01 cascade;