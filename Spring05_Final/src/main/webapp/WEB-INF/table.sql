CREATE TABLE users
(id VARCHAR2(30) PRIMARY KEY,
pwd VARCHAR2(100) NOT NULL,
email VARCHAR2(30),
regdate DATE,
profile VARCHAR2(100));
CREATE TABLE board_cafe(
	num NUMBER PRIMARY KEY,
	writer VARCHAR2(100) NOT NULL, -- �� �ۼ����� id 
	title VARCHAR2(100) NOT NULL,
	content CLOB,
	viewCount NUMBER, -- ��ȸ��
	regdate DATE
);
CREATE SEQUENCE board_cafe_seq;
CREATE TABLE board_file(
num NUMBER PRIMARY KEY,
writer VARCHAR2(100),
title VARCHAR2(100) NOT NULL,
orgFileName VARCHAR2(100) NOT NULL, -- ���� ���ϸ�
saveFileName VARCHAR2(100) NOT NULL, -- ���� �ý��ۿ� ���� ����� ���ϸ�
fileSize NUMBER, -- ������ ũ�� (byte)
downCount NUMBER DEFAULT 0, -- �ٿ�ε� Ƚ�� 
regdate DATE
);
CREATE SEQUENCE board_file_seq;
ALTER TABLE users ADD(profile VARCHAR2(50));
-- ��� ������ ������ ���̺�
CREATE TABLE board_cafe_comment(
	num NUMBER PRIMARY KEY, -- ����� �۹�ȣ
	writer VARCHAR2(100), -- ��� �ۼ���
	content VARCHAR2(500), -- ��� ����
	target_id VARCHAR2(100), -- ����Ǵ���̵Ǵ¾��̵�(���ۼ���)
	ref_group NUMBER, -- ��� �׷��ȣ
	comment_group NUMBER, -- ���ۿ� �޸� ��� �������� �׷��ȣ
	deleted CHAR(3) DEFAULT 'no', -- ����� ���� �Ǿ����� ���� 
	regdate DATE -- ��� ����� 
);

CREATE SEQUENCE board_cafe_comment_seq;

CREATE TABLE shop(
	num NUMBER PRIMARY KEY, --��ǰ��ȣ
	name VARCHAR2(30), --��ǰ�̸�
	price NUMBER, --��ǰ����
	remainCount NUMBER CHECK(remainCount >= 0) --����� 
);

-- �� ���� ���̺�
CREATE TABLE client_account(
	id VARCHAR2(30) PRIMARY KEY, -- ���� ���̵�
	money NUMBER CHECK(money >= 0), -- ���� �ܰ� 
	point NUMBER
);

-- �ֹ� ���̺�
CREATE TABLE client_order(
	num NUMBER PRIMARY KEY, -- �ֹ���ȣ
	id VARCHAR2(30), -- �ֹ� ���� ���̵�
	code NUMBER, -- �ֹ��� ��ǰ�� ��ȣ 
	addr VARCHAR2(50) -- ��� �ּ�
);

-- �ֹ� ���̺� ����� ������ 
CREATE SEQUENCE client_order_seq;


-- sample ������
INSERT INTO shop (num,name,price,remainCount)
VALUES(1, '���', '1000', 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(2, '�ٳ���', '2000', 5);

INSERT INTO shop (num,name,price,remainCount)
VALUES(3, '��', '3000', 5);

INSERT INTO client_account (id, money, point)
VALUES('superman', 10000, 0);

INSERT INTO client_account (id, money, point)
VALUES('batman', 10000, 0);
