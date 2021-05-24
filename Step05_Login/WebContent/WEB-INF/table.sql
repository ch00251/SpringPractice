CREATE TABLE users
(id VARCHAR2(30) PRIMARY KEY,
pwd VARCHAR2(30) NOT NULL,
email VARCHAR2(30),
regdate DATE);

create table board_cafe(
	num number primary key,
	writer varchar2(100) not null,
	title varchar2(100)not null,
	content clob,
	viewCount number,
	regdate date
);

create sequence board_cafe_seq;