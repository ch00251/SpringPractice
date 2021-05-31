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

create table board_file(
num number primary key,
writer varchar2(100),
title varchar2(100) not null,
orgFileName varchar2(100) not null, --원본 파일명
saveFileName varchar2(100) not null, --파일 시스템에 실제 저장된 파일명
fileSize number, --파일의 크기(byte)
downCount number default 0, -- 다운로드 횟수
regdate date );

create sequence board_file_seq;

alter table users add(profile varchar2(50));

