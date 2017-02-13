/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.6.23-log : Database - cstax
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cstax` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cstax`;

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `detp_id` varchar(32) NOT NULL,
  `org_id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`detp_id`),
  KEY `FK_org_dept` (`org_id`),
  CONSTRAINT `FK_org_dept` FOREIGN KEY (`org_id`) REFERENCES `org` (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

/*Table structure for table `emp_role` */

DROP TABLE IF EXISTS `emp_role`;

CREATE TABLE `emp_role` (
  `emp_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`,`role_id`),
  KEY `FK_emp_role2` (`role_id`),
  CONSTRAINT `FK_emp_role` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`),
  CONSTRAINT `FK_emp_role2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emp_role` */

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `emp_id` varchar(32) NOT NULL,
  `detp_id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `FK_dept_emp` (`detp_id`),
  CONSTRAINT `FK_dept_emp` FOREIGN KEY (`detp_id`) REFERENCES `dept` (`detp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

/*Table structure for table `leader` */

DROP TABLE IF EXISTS `leader`;

CREATE TABLE `leader` (
  `emp_id` varchar(32) NOT NULL,
  `detp_id` varchar(32) DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  CONSTRAINT `FK_Inheritance_1` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `leader` */

/*Table structure for table `org` */

DROP TABLE IF EXISTS `org`;

CREATE TABLE `org` (
  `org_id` varchar(32) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `org` */

/*Table structure for table `privilege` */

DROP TABLE IF EXISTS `privilege`;

CREATE TABLE `privilege` (
  `pri_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pri_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `privilege` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

/*Table structure for table `role_pri` */

DROP TABLE IF EXISTS `role_pri`;

CREATE TABLE `role_pri` (
  `role_id` varchar(32) NOT NULL,
  `pri_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`pri_id`),
  KEY `FK_own` (`pri_id`),
  CONSTRAINT `FK_belong` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `FK_own` FOREIGN KEY (`pri_id`) REFERENCES `privilege` (`pri_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_pri` */

/*Table structure for table `t_complain` */

DROP TABLE IF EXISTS `t_complain`;

CREATE TABLE `t_complain` (
  `comp_id` varchar(32) NOT NULL,
  `comp_company` varchar(100) DEFAULT NULL,
  `comp_name` varchar(20) DEFAULT NULL,
  `comp_mobile` varchar(20) DEFAULT NULL,
  `is_NM` tinyint(1) DEFAULT NULL,
  `comp_time` datetime DEFAULT NULL,
  `comp_title` varchar(200) NOT NULL,
  `to_comp_name` varchar(20) DEFAULT NULL,
  `to_comp_dept` varchar(100) DEFAULT NULL,
  `comp_content` text,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_complain` */

insert  into `t_complain`(`comp_id`,`comp_company`,`comp_name`,`comp_mobile`,`is_NM`,`comp_time`,`comp_title`,`to_comp_name`,`to_comp_dept`,`comp_content`,`state`) values ('1','部门A','用户1','13888888888',1,'2015-04-02 11:00:00','投诉1','管理员','部门A','<p>投诉内容</p>\r\n1','1'),('2','部门B','用户1','13800000000',0,'2015-08-02 11:00:00','投诉2','管理员','部门B','投诉内容\r\n2','2'),('8a96363059826366015982643f0a0000','部门A','管理员',NULL,0,'2016-12-09 16:42:05','test','用户1test','部门A','<p>投诉用户1test</p>','2'),('8a96363059826366015982702fc70001','部门A','管理员','12345678910',0,'2017-01-09 16:55:07','投诉4','用户1','部门B','<p><img src=\"http://localhost:8080/csTax/upload/ueditor/image/20170109/1483952085416046662.jpg\" title=\"1483952085416046662.jpg\" alt=\"Penguins.jpg\" width=\"285\" height=\"196\" style=\"width: 285px; height: 196px;\"/>企鹅飞</p>','2'),('8a9636305982636601598273166a0002','部门A','管理员','12345678910',1,'2017-01-09 16:58:18','投诉5','用户2','部门B','<p><img src=\"http://img.baidu.com/hi/jx2/j_0003.gif\"/>呵呵呵</p>','1');

/*Table structure for table `t_complain_reply` */

DROP TABLE IF EXISTS `t_complain_reply`;

CREATE TABLE `t_complain_reply` (
  `reply_id` varchar(32) NOT NULL,
  `comp_id` varchar(32) NOT NULL,
  `replyer` varchar(20) DEFAULT NULL,
  `reply_dept` varchar(100) DEFAULT NULL,
  `reply_time` datetime DEFAULT NULL,
  `reply_content` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`reply_id`),
  KEY `FK_comp_reply` (`comp_id`),
  CONSTRAINT `FK_comp_reply` FOREIGN KEY (`comp_id`) REFERENCES `t_complain` (`comp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_complain_reply` */

insert  into `t_complain_reply`(`reply_id`,`comp_id`,`replyer`,`reply_dept`,`reply_time`,`reply_content`) values ('8a963630597caf7901597cb2bfd10002','1','管理员','部门A','2017-01-08 14:10:05','xxx123'),('8a963630597cd80c01597ceb806e0000','1','管理员','部门A','2017-01-08 15:12:06','2222123'),('8a9636305982a8e5015982a969c50000','8a9636305982636601598273166a0002','管理员','部门A','2017-01-09 17:57:38','hahah'),('8a9636305982a8e5015982a9d9850001','8a9636305982636601598273166a0002','管理员','部门A','2017-01-09 17:58:06',''),('8a9636305982a8e5015982aa80d70002','8a9636305982636601598273166a0002','管理员','部门A','2017-01-09 17:58:49',''),('8a9636305982ad18015982adb7600000','8a9636305982636601598273166a0002','管理员','部门A','2017-01-09 18:02:20','11111111');

/*Table structure for table `t_dept` */

DROP TABLE IF EXISTS `t_dept`;

CREATE TABLE `t_dept` (
  `deptId` varchar(255) NOT NULL,
  `deptName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_dept` */

insert  into `t_dept`(`deptId`,`deptName`) values ('8a963608594d35d801594d35d9d70000','部门A'),('8a963608594d363c01594d363d450000','部门B');

/*Table structure for table `t_info` */

DROP TABLE IF EXISTS `t_info`;

CREATE TABLE `t_info` (
  `info_id` varchar(32) NOT NULL,
  `type` varchar(10) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `content` longtext,
  `memo` varchar(200) DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_info` */

insert  into `t_info`(`info_id`,`type`,`source`,`title`,`content`,`memo`,`creator`,`create_time`,`state`) values ('8a9636305972d573015972defcdc0001','tzgg','','信息2','<p>hahah</p>','','管理员','2017-01-06 16:22:05','1'),('8a9636305972f13f015972fa6ed80000','tzgg','','信息3','<p>111</p>','','管理员','2017-01-06 16:52:05','1'),('8a96363059739d72015973a129fb0000','tzgg','','信息','<p>1111111</p>','','管理员','2017-01-06 19:54:12','1'),('8a9636305973cfa2015973d048810000','tzgg','','通告2','<p>1111</p>','','管理员','2017-01-06 20:45:40','1'),('8a9636305973cfa2015973d096490001','nszd','','指导2','<p>指导指导</p>','','管理员','2017-01-06 20:45:48','1');

/*Table structure for table `t_month` */

DROP TABLE IF EXISTS `t_month`;

CREATE TABLE `t_month` (
  `imonth` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_month` */

insert  into `t_month`(`imonth`) values (1),(2),(3),(4),(5),(6),(7),(8),(9),(10),(11),(12);

/*Table structure for table `t_person` */

DROP TABLE IF EXISTS `t_person`;

CREATE TABLE `t_person` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_person` */

insert  into `t_person`(`id`,`name`) values ('8a963608594a2eed01594a2eeea20000','人员1'),('8a963608594a503b01594a503c270000','人员2');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `role_id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `state` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`role_id`,`name`,`state`) values ('8a9636305968a629015968a7e0220000','管理员','1'),('8a96363059691b1301596928bcfc0000','一般用户','1'),('8a963630596db02001596dc184100000','test','1');

/*Table structure for table `t_role_privilege` */

DROP TABLE IF EXISTS `t_role_privilege`;

CREATE TABLE `t_role_privilege` (
  `role_id` varchar(32) NOT NULL,
  `code` varchar(20) NOT NULL,
  PRIMARY KEY (`role_id`,`code`),
  KEY `FK45FBD628BCF3728A` (`role_id`),
  CONSTRAINT `FK45FBD628BCF3728A` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_privilege` */

insert  into `t_role_privilege`(`role_id`,`code`) values ('8a9636305968a629015968a7e0220000','hqfw'),('8a9636305968a629015968a7e0220000','nsfw'),('8a9636305968a629015968a7e0220000','space'),('8a9636305968a629015968a7e0220000','xzgl'),('8a9636305968a629015968a7e0220000','zxxx'),('8a96363059691b1301596928bcfc0000','space'),('8a96363059691b1301596928bcfc0000','zxxx'),('8a963630596db02001596dc184100000','space');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `account` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `headImg` varchar(100) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  `memo` varchar(200) DEFAULT NULL,
  `dept` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`name`,`account`,`password`,`headImg`,`gender`,`email`,`mobile`,`birthday`,`state`,`memo`,`dept`) values ('8a963608594e538501594e5441330000','管理员','admin','123456','user/fbdfdad7adbe4143b37262f10bff2e80.jpg','\0','123456@qq.com','12345678910','1993-12-23 00:00:00','1','','部门A'),('8a963608594e5daf01594e8404b70000','用户1','user1','123456','user/921bfaa1c07347d59163197ac27e4288.jpg','','5487527@qq.com','1524879876','2012-12-11 00:00:00','1','','部门B'),('8a96363059588c950159588d8a760000','用户2','user2','123123','user/0bf6808b-268c-4add-8e44-b8f1ceb7f4f6.jpg','\0','1235@qq.com','1884578865','2012-01-17 00:00:00','1','','部门B'),('8a9636305963f2cb015963f4d6010000','用户1test','user3','123456',NULL,'','user1@itcast.com','13888888888','1999-10-10 00:00:00','1','','部门A');

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `role_id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FK143BF46ABCF3728A` (`role_id`),
  CONSTRAINT `FK143BF46ABCF3728A` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`role_id`,`user_id`) values ('8a9636305968a629015968a7e0220000','8a963608594e538501594e5441330000'),('8a96363059691b1301596928bcfc0000','8a963608594e538501594e5441330000'),('8a96363059691b1301596928bcfc0000','8a963608594e5daf01594e8404b70000'),('8a96363059691b1301596928bcfc0000','8a96363059588c950159588d8a760000'),('8a96363059691b1301596928bcfc0000','8a963630596976c50159697a76120000'),('8a963630596db02001596dc184100000','8a9636305963f2cb015963f4d6010000');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
