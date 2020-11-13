create table Board(
board_id number primary key, 
title varchar(100),
writer varchar(50),
content varchar(200),
regdate DATE DEFAULT sysdate
);

CREATE SEQUENCE seq_board
INCREMENT BY 1
START WITH 1;

	
CREATE  TABLE board_member(
	member_id NUMBER PRIMARY KEY,
	m_id varchar(20),
	m_pass varchar(20),
	m_name varchar(20),
	regdate DATE DEFAULT sysdate
)

CREATE SEQUENCE seq_board_member
INCREMENT BY 1
START WITH 1;

INSERT INTO board(board_id, title, WRITER, CONTENT) values(seq_board.NEXTVAL,'첫 게시글', 'cjh960504', '반갑습니다~');