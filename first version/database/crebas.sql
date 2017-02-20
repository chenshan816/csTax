/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/1/14 15:52:45                           */
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

