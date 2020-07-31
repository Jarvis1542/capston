/* SQL CommandLine 기준 */

conn sys as sysdba;
1234

create user cap2 identified by 1234;
grant connect, resource, dba to cap2;

conn cap2/1234;

commit;
