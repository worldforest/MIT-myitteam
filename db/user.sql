use mydb;
select * from user;
insert into user (email, pwd, name, nickname, age, gender, major, city, gu, dong, details)
values("dmin@naver.com", 'admin', '관리자','admin', '99', '여자', '컴퓨터공학', '대전시', '유성구', '봉명동', '봉명카페');
insert into user (email, pwd, name, nickname, age, gender, major, city, gu, dong, details)
values("serim@naver.com", 'qwer1234', '이세림','rim', '24', '여자', '정보통신', '대전시', '유성구', '관평동', '배울1로');
insert into user (email, pwd, name, nickname, age, gender, major, city, gu, dong, details)
values("hjmin@naver.com", 'jm0701', '홍정민','min', '27', '남자', '전자공학', '부산시', '사하구', '다대동', '다대포로');
delete from user where email like "hjmin%";