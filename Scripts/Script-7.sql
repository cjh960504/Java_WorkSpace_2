
SELECT * FROM MEMBER;

CREATE TABLE MEMBER (
	member_idx NUMBER PRIMARY KEY,
	memeber_id varchar(20),
	password varchar(20),
	name varchar(20),
	nickname varchar(20)
)

CREATE SEQUENCE seq_member
INCREMENT BY 1
START WITH 1;

CREATE SEQUENCE seq_member
INCREMENT BY 1
START WITH 1;

INSERT INTO member(member_idx, member_id, password, name, nickname) values(seq_member.nextval, 'qq0504', 'asd1234', '최준혁', '준핵');

ALTER TABLE MEMBER MODIFY memeber_id member_id;
