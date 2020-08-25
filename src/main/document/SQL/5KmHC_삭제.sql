/* 시퀀스 삭제 */
drop sequence qna_board_seq;
drop sequence scs_pay_seq;
drop sequence scs_book_seq;
drop sequence hp_pay_seq;
drop sequence hp_book_seq;
drop sequence entry_info_seq;

/* 뷰 삭제 */
drop view qna_board_view;
drop view hp_bookmark_view;
drop view hp_pay_view;
drop view hp_book_view;
drop view hp_view;
drop view scs_bookmark_view;
drop view scs_pay_view;
drop view scs_book_view;
drop view scs_view;
drop view entry_info_view;
drop view mp_view;
drop view res_view;
drop view reg_view;
drop view car_view;
drop view member_view;

/* 테이블 삭제 */
drop table qna_board;
drop table hp_bookmark;
drop table hp_pay;
drop table hp_book;
drop table hp;
drop table scs_bookmark;
drop table scs_pay;
drop table scs_book;
drop table scs;
drop table entry_info;
drop table mp;
drop table res;
drop table reg;
drop table car;
drop table member;