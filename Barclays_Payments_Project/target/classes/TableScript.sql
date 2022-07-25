drop database if exists user_db;
create database user_db;
use  user_db;


create table user(
   login_id int auto_increment,
   password varchar(50),
   linked_account_sequence_id varchar(20),
	role_name varchar(50),
   role_id int,
   constraint ps_user_id_pk primary key (login_id)
);

create table role
(
	role_id int,
	name varchar(45),
	constraint ps_role_id_pk primary key (role_id)
);

insert into user (login_id, role_name, password,linked_account_sequence_id, role_id ) values (1001,'Bank_Manager','hello','101',1);



commit;
select * from user;