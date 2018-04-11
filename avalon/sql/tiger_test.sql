create table province(
	id int primary key auto_increment,
	province_code SMALLINT,
	province_name varchar(32)
)

insert into province(province_code,province_name)values
(103,'浙江省'),
(104,'安徽省');


CREATE table t_file(
id int not null auto_increment,
file blob,
primary key(id)
)

DELIMITER ;;
CREATE PROCEDURE `procedure_student`(IN par_name varchar(20),OUT total_count int, OUT exec_date date)
BEGIN
	select count(*) from student where name like '%'||par_name||'%';
	SELECT now() INTO exec_date;
END
;;
DELIMITER ;


--select * from student


