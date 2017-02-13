/*==============================================================*/
/* DBMS name:      MySQL 5.0 
/* Author:		   cs
/* Desc			        创建投诉受理架构数据库表                                   
/* Created on:     2017/1/8			                            */
/*==============================================================*/


drop table if exists t_complain;

drop table if exists t_complain_reply;

/*==============================================================*/
/* Table: t_complain                                            */
/*==============================================================*/
create table t_complain
(
   comp_id              varchar(32) not null,
   comp_company         varchar(100),
   comp_name            varchar(20),
   comp_mobile          varchar(20),
   is_NM                bool,
   comp_time            datetime,
   comp_title           varchar(200) not null,
   to_comp_name         varchar(20),
   to_comp_dept         varchar(100),
   comp_content         text,
   state                varchar(1),
   primary key (comp_id)
);

/*==============================================================*/
/* Table: t_complain_reply                                      */
/*==============================================================*/
create table t_complain_reply
(
   reply_id             varchar(32) not null,
   comp_id              varchar(32) not null,
   replyer              varchar(20),
   reply_dept           varchar(100),
   reply_time           datetime,
   reply_content        varchar(300),
   primary key (reply_id)
);

alter table t_complain_reply add constraint FK_comp_reply foreign key (comp_id)
      references t_complain (comp_id) on delete restrict on update restrict;

      
/*==============================================================*/
/* DBMS name:      MySQL 5.0 
/* Author:		   cs
/* Desc			        创建咨询受理架构数据库表                                   
/* Created on:     2017/1/13			                            */
/*==============================================================*/

drop table if exists consult_reply;

drop table if exists t_consult;

/*==============================================================*/
/* Table: consult_reply                                         */
/*==============================================================*/
create table consult_reply
(
   reply_id             varchar(32) not null,
   consId               varchar(32) not null,
   replyer              varchar(20),
   reply_dept           varchar(100),
   reply_time           datetime,
   reply_content        varchar(300),
   primary key (reply_id)
);

/*==============================================================*/
/* Table: t_consult                                             */
/*==============================================================*/
create table t_consult
(
   consTitle            varchar(32),
   consId               varchar(32) not null,
   consContent          text,
   consTime             datetime,
   state                varchar(2),
   consDept             varchar(100),
   consName             varchar(20),
   consMobile           varchar(20),
   primary key (consId)
);

alter table consult_reply add constraint FK_belong foreign key (consId)
      references t_consult (consId) on delete restrict on update restrict;

/*==============================================================*/
/* DBMS name:      MySQL 5.0 
/* Author:		   cs
/* Desc			        创建预约事项架构数据库表                                   
/* Created on:     2017/1/8			                            */
/*==============================================================*/
drop table if exists reserveItem;

/*==============================================================*/
/* Table: reserveItem                                           */
/*==============================================================*/
create table reserveItem
(
   resItem_id           varchar(32) not null,
   item_id              varchar(20),
   resItem_name         varchar(20),
   resItem_dept         varchar(20),
   resItem_toName       varchar(20),
   state                bool,
   primary key (resItem_id)
);
