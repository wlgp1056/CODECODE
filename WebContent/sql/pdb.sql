create table mem(
ID  VARCHAR2(20) primary key,
PASSWORD VARCHAR2(30),                      
NAME VARCHAR2(30),
GENDER  VARCHAR2(20),
EMAIL  VARCHAR2(50));
 
create table bbs2(
bbsid number primary key,
bbstitle varchar2(80),
bbscontent varchar2(1000),
bbsdate date,
bbshit number,
bbscategory varchar2(50),
id VARCHAR2(20),
BBSAVAILABLE number,
B_FNAME VARCHAR2(100));

create table comm2(
commID number primary key,
bbsID number,
ID varchar2(20),
commDate date,
commText varchar2(1000),
commAvailable number,
CONSTRAINT FK_COMM2 FOREIGN KEY(bbsID) REFERENCES bbs2(bbsID)
);

 