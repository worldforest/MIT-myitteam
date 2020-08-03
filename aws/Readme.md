### Mysql workbench에서 AWS 계정으로 접속하기

- Connection 생성

1. new connection
2. Connection Method : Standard TCP/IP over SSH 선택
3. SSH Hostname : i3b306.p.ssafy.io
4. SSH Username : ubuntu
5. SSH password는 설정하지 않는다
6. SSH Key File : pem 파일이 있는 위치
7. MySQL Hostname : 기본 127.0.0.1
8. MySQL Server Port : 3306
9. Username : aws에 생성한 계정(MIT)
10. Password : 계정의 비밀번호(ssafyB306)

---

- 계정 권한 설정
1. Administration에서 Users and Privileges
2. 원하는 계정 선택
3. Administrative Roles에서 DBA(전체 권한) 선택


---

- 되기 전에 이런 것들을 했다

1. mysql -u root -p
2. password 입력 (root계정 비밀번호 : ssafyssafy)
3. use mysql
4. update user set password=PASSWORD('ssafyssafy') where user='root'; //여기서 바꿨어
5. flush privileges;
6. grant all privileges on *.* to root@'%' identified by 'ssafyssafy'
7. service mysql start
8. mysql_upgrade -u root -p
9. 비밀번호 입력