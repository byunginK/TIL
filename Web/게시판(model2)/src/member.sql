DROP TABLE MEMBER
CASCADE CONSTRAINTS;

CREATE TABLE MEMBER2(
	ID VARCHAR2(50) PRIMARY KEY,
	PWD VARCHAR2(50) NOT NULL,
	NAME VARCHAR2(50) NOT NULL,
	BIRTH DATE NOT NULL,
	GENDER VARCHAR2(5),
	EMAIL VARCHAR2(50) UNIQUE,
	PHONE NUMBER(10),
	AUTH NUMBER(1) NOT NULL
);