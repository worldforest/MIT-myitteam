## 배포 서버(AWS)

서버명 : i3B306.p.ssafy.io

인증키 : i3B306T.pem

리눅스에서 접속방법 : ssh -i ubuntu@i3B306.p.ssafy.io



MySQL Workbench로 AWS MariaDB 연결 설정

---

[ Setup New Connection ]

Connection Name / workbench 구분용

Connection Method / Standard TCP/IP over SSH

SSH Hostname / EC2 instance의 IP

SSH Username / EC2 user id

SSH Password / Key file 을 쓸 것이므로 패스

SSH Key File / pem 파일 경로

MySQL Hostname / RDS endpoint(모든 호스트 허용 %)

MySQL Server Port / 디폴트

Username / DB 설치 시 설정했던 user id

Password / DB 설치 시 설정했던 비번

Default Schema / 기본 db

---



