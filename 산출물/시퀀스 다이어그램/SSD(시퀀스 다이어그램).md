# MIT(My IT Team)



## 1. 로그인



로그인은 email, PW 입력 후 DB를 확인해 로그인을 진행

```mermaid
sequenceDiagram
autonumber
LoginPage->SpringServer:email/PW 입력
SpringServer->DB:회원정보 확인
DB->Home:로그인 완료


```

## 2. 회원가입

email, nickname 중복검사 후 회원가입 진행

```mermaid
sequenceDiagram
autonumber
SignupPage->SpringServer:회원가입 정보
SpringServer->DB:email, nicknmae 중복검사
DB->Home:회원가입 성공


```

## 3. 계정설정

로그인 후 이용가능, 수정된 데이터를 저장후 갱신

```mermaid
sequenceDiagram
autonumber
UserPage->SpringServer:회원정보 수정
SpringServer->DB:수정된 데이터
DB->UserPage:수정된 정보 반영


```

## 4. 유저페이지(profile)

유저정보(닉네임, 팔로우, 자기소개 등) 보여주고 유저의 피드를 보여줌

```mermaid
sequenceDiagram
autonumber
UserPage->SpringServer:로그인/검색된 유저 정보
SpringServer->DB:유저 정보 확인
DB->UserPage:프로필 보여주기(유저정보, 피드)


```

## 5. 메인 페이지

인기 공모전, 프로젝트를 보여준다. 클릭 시 해당 공모전, 프로젝트로 이동

```mermaid
sequenceDiagram
autonumber
MainPage->SpringServer:공모전 선택
SpringServer->DB:공모전 정보 검색
DB->DetailPage:선택한 공모전
MainPage->SpringServer:프로젝트 선택
SpringServer->DB:프로젝트 정보 검색
DB->DetailPage:선택한 프로젝트
```

## 6. 공모전 페이지

#### 공모전 팀 지원하기

```mermaid
sequenceDiagram
autonumber
ContestPage->DetailPage:공모전 선택
DetailPage->SpringServer:팀원 모집 선택
SpringServer->DB:전체 팀 정보 확인
DB->TeamPage:팀 전체 목록
TeamPage->SpringServer:팀 선택
SpringServer->DB:팀 상세정보 검색
DB->TeamDetailPage:팀 상세정보
TeamDetailPage->SpringServer:팀 지원하기
SpringServer->DB:apply
DB->TeamDetailPage:지원요청 성공
```

#### 공모전 팀 등록하기

```mermaid
sequenceDiagram
autonumber
ContestPage->DetailPage:공모전 선택
DetailPage->SpringServer:팀원 모집 선택
SpringServer->DB:전체 팀 정보 확인
DB->TeamPage:팀 전체 목록
TeamPage->SpringServer:팀 등록하기
SpringServer->DB:팀 정보 저장
DB->TeamPage:팀 전체 목록 조회
```

## 7. 프로젝트 페이지

#### 프로젝트 지원하기

```mermaid
sequenceDiagram
autonumber
ProjectPage->SpringServer:전체 프로젝트 조회
SpringServer->DB:프로젝트 정보 확인
DB->ProjectPage:전체 프로젝트 조회
ProjectPage->SpringServer:프로젝트 지원하기
SpringServer->DB:지원 정보
DB->ProjectPage:지원 성공/실패 전달
DB->TeamApplyPage:팀 지원 관리페이지
```

#### 프로젝트 등록하기

```mermaid
sequenceDiagram
autonumber
ProjectPage->SpringServer:전체 프로젝트 조회
SpringServer->DB:프로젝트 정보 확인
DB->ProjectPage:전체 프로젝트 조회
ProjectPage->SpringServer:프로젝트 등록하기
SpringServer->DB:프로젝트 정보 전달
DB->ProjectPage:프로젝트 등록 
```

## 8. 팀 관리 페이지

팀 생성 후 팀원 정보를 받아온다. 일정관리(회의 가능 날짜, 회의장소 추천)

```mermaid
sequenceDiagram
autonumber
TeamPage->Scheduling:일정 관리
Scheduling->SpringServer:일정 등록
SpringServer->DB:팀원별 일정 저장
DB->SpringServer:회의 가능 날짜찾기
SpringServer->Scheduling:팀 회의날짜 보여주기
Scheduling->SpringServer:회의장소 추천
SpringServer->DB:최적 장소찾기
DB->Scheduling:팀원 위치기반 장소 추천

```
