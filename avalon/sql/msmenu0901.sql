/*
-- Query: SELECT * FROM tiger.ms_menu
LIMIT 0, 1000

-- Date: 2017-09-01 16:57
*/
INSERT INTO `ms_menu` (`id`,`menuid`,`menuname`,`menuurl`,`fatherid`,`type`,`sortno`,`isactive`,`createtime`,`creatorcode`) VALUES (1,'1','系统管理',NULL,NULL,'1','1','1','2017-08-30 16:04:12','001');
INSERT INTO `ms_menu` (`id`,`menuid`,`menuname`,`menuurl`,`fatherid`,`type`,`sortno`,`isactive`,`createtime`,`creatorcode`) VALUES (2,'2','用户管理','../../menu/user','1','2','2','1','2017-08-30 17:46:49','001');
INSERT INTO `ms_menu` (`id`,`menuid`,`menuname`,`menuurl`,`fatherid`,`type`,`sortno`,`isactive`,`createtime`,`creatorcode`) VALUES (3,'3','角色管理','../../menu/role','1','2','3','1','2017-08-30 17:46:49','001');
INSERT INTO `ms_menu` (`id`,`menuid`,`menuname`,`menuurl`,`fatherid`,`type`,`sortno`,`isactive`,`createtime`,`creatorcode`) VALUES (4,'4','菜单管理','../../menu/menu','1','2','4','1','2017-08-30 17:46:49','001');
INSERT INTO `ms_menu` (`id`,`menuid`,`menuname`,`menuurl`,`fatherid`,`type`,`sortno`,`isactive`,`createtime`,`creatorcode`) VALUES (5,'5','授权管理','../../menu/auth','1','2','5','1','2017-08-30 17:46:49','001');
INSERT INTO `ms_menu` (`id`,`menuid`,`menuname`,`menuurl`,`fatherid`,`type`,`sortno`,`isactive`,`createtime`,`creatorcode`) VALUES (6,'6','测试菜单','../../test/index2.jsp','1','2','6','1','2017-08-30 17:03:56','001');
INSERT INTO `ms_menu` (`id`,`menuid`,`menuname`,`menuurl`,`fatherid`,`type`,`sortno`,`isactive`,`createtime`,`creatorcode`) VALUES (7,'7','测试菜单2','../../test/index2.jsp','1','2','7','1','2017-08-30 17:03:56','001');

select * from ms_menu;

update ms_menu set menuurl ='../../index2.jsp' where id='6';
update ms_menu set menuurl ='../../paper/test/websocket.jsp' where id='7';
