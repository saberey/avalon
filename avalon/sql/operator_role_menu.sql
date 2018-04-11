--操作员表
--drop table ms_operator
drop table if exists ms_operator;
create table ms_operator(
	id  int primary key  auto_increment,
	operatorcode varchar(12),
	operatorname varchar(36),
	operatorpwd  varchar(30),
	departmentid int,
	groupid int,
	isactive char(1),
	createtime Timestamp,
	creatorcode varchar(12)
)
--select * from ms_operator;
--角色表
drop table if exists ms_role;
create table ms_role(
	id  int primary key  auto_increment,
	roleid varchar(12),
	rolename varchar(36),
	accessmenuid varchar(12),
	isactive char(1),
	createtime Timestamp,
	creatorcode varchar(12)
)
alter table ms_role change accessmenuid fatherroleid varchar(12);


--菜单表
drop table if exists ms_menu;
create table ms_menu(
	id  int primary key  auto_increment,
	menuid varchar(12),
	menuname varchar(36),
	menuurl varchar(100),
	fatherid varchar(12),
	type varchar(10),
	sortno  varchar(12),
	isactive char(1),
	createtime Timestamp,
	creatorcode varchar(12)
)
--用户-角色表
drop table if exists ms_operator_role;
create table ms_operator_role(
	id  int primary key  auto_increment,
	operatorcode varchar(12),
	roleid varchar(12),
	isactive char(1),
	createtime Timestamp,
	creatorcode varchar(12),
	modifytime Timestamp,
	modifycode varchar(12)
)

--角色-菜单表
drop table if exists ms_role_menu;
create table ms_role_menu(
	id  int primary key  auto_increment,
	roleid varchar(12),
	menuid varchar(12),
	isactive char(1),
	createtime Timestamp,
	creatorcode varchar(12),
	modifytime Timestamp,
	modifycode varchar(12)
)