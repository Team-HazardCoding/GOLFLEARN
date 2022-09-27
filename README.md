# GOLFLEARN
This is a golf platform for golf-beginner(a.k.a. 골린이)

## 프로젝트 개요
### 프로젝트명 : GOLF LEARN

이 서비스는 골린이들을 위한 골프서비스입니다.

<br>
[HAZARD의 노션페이지]https://www.notion.so/1-by-Hazard_Coding-94bca756a1f440e08e96e2ca63f4bb10 👈 link <br>

### 개발기간 <br>
22.06.22(수) ~ 22.07.15(금)<br>
24일간(기획 3일, 설계 7일, 구현 14일)<br>

### 전체일정 프로세스
1 ~ 3일차 : 프로젝트 기획, 기능 선정, 역할 분담<br>
4 ~ 10일차 : 설계<br>
11 ~ 24일차 : 코드구현<br>

## 서비스 기능

### 1. 메인 페이지
- 메인페이지 load 시에 DB에있는 모든 레슨들 조회하여 보여줌
- 시도, 시군구 api를 활용하여 자신의 지역에 맞게 필터링하여 레슨을 볼 수 있음
- 레슨들은 등록된 최신순으로 필터링되어 보여짐

### 2. 회원가입 페이지
- 수강생과 레슨프로를 선택하여 가입할 수 있게 함
- 이미지를 삽입할 수 있도록 구현함
- 회원가입 시에 유효성검사를 통해 잘못된 값이 들어갈수 없게 함

### 3. 로그인/로그아웃 페이지
- DB에 저장된 회원 정보와 일치시 로그인이 됨
- 세션에 로그인정보, user_type을 저장하여 추후 사용되도록 함
- 로그아웃 시 세션에 저장된 로그인 정보, user_type삭제

### 4. 아이디/비밀번호찾기 페이지
- 아이디 찾기 : DB에 저장되어 있는 이름, 이메일 주소가 일치하면 모달창으로 값 전달
- 비밀번호 찾기 : 메일로 인증코드를 전달함, smtp사용(자바 메일 라이브러리로 구글 smtp사용함)

### 5. 레슨상세보기 페이지
- 메인페이지에서 선택한 레슨에 대해 상세정보를 보여줌
- 팀원의 시군구API기술을 활용하여 수정 기술 
- 최상단 이동버튼, 해당 링크로 스크롤무브 기술

### 6. 레슨등록 페이지
- 사용자(프로)가 강의할 레슨에 대해 필요한 정보들을 입력할 항목들을 로딩
- 팀원의 업로드 이미지기술을 활용하여 수정 기술
- 업로드할 이미지의 파일의 크기, 용량 제한 및 확장자 유효성검사
- 최상단 이동버튼 기술

### 7. 마이페이지 페이지
- 수강생 / 프로의 마이페이지가 존재함
- 수강생 마이페이지에서는 지금까지 수강한, 수강중인, 수강 예정인 강의들을 보여줌
- 해당 강의에 대해 후기를 작성 / 수정하는 페이지로 연결하며 수강취소가 가능함
- 프로의 마이페이지에서는 프로가 강의하는 레슨들이 보여짐
- 레슨재개, 레슨중단 버튼을 통해 레슨 모집상태 변경(예정)  

### 8. 리뷰 페이지
- 수강생이 들은 강의에 대해 별점과 리뷰상세를 작성 / 수정 가능

### 9. 수강생 관리 페이지
- 담당 수강생들의 개인정보 및 수강정보 확인 가능
- 수강확인 버튼 클릭을 통해 출석 횟수 증가 가능(예정)

## 구현기능
- 이미지 업로드
- 회원가입 시 유효성검사
- SMTP를 활용한 비밀번호 찾기
- 지역api를 활용한 지역별 레슨필터링
- 레슨 등록 / 수정
- job scheduler 이용한 DB자동 업데이트
- 이미지 드래그 앤 드랍(예정)
- 소셜 로그인 기능(예정)
- 채팅 시스템 및 알림(예정)

## 사용도구
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=HTML5&logoColor=white"><img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white"><img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white"><img src="https://img.shields.io/badge/jQuery-0769AD?style=for-the-badge&logo=jQuery&logoColor=white"><br>
<img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=for-the-badge&logo=Apache Tomcat&logoColor=white"><img src="https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=Oracle&logoColor=white"><br>
<img src="https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white"><img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white"><br>
<img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=Slack&logoColor=white"><img src="https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=Discord&logoColor=white">

### Collaboration & Tools
- Slack
- Figma
- ERD cloud
- starUML

## 팀빌딩 및 역할
- KOSTA 열공러들로 구성
- 해가져도 코딩하는 마인드 탑재
<img width="397" alt="스크린샷" src="https://user-images.githubusercontent.com/104618655/179953057-a8df32d5-b2f9-4185-b53d-e13d738eaf2f.jpg">

### 개발자 (가나다순)<br>
**박종우 [@jongsaewoo](https://github.com/JongSaewoo) Dev**<br>
✔️ 레슨상세 페이지 담당<br>
✔️ 레슨등록 페이지 담당<br>
✔️ 팀원의 시군구api기술 및 이미지업로드 기술을 수정활용<br>
✔️ (JS)업로드할 이미지의 파일의 크기, 용량 제한 및 확장자 유효성검사<br>
✔️ 담당 페이지 css 구현<br>
<br>
**전승현 [@cokesh](https://github.com/cokesh) PL**<br>
✔️ 메인 페이지 담당<br>
✔️ 지역API를 활용한 필터링 구현<br>
✔️ HTML 프레임 구현 <br>
✔️ Git, Github 관리<br>
✔️ 담당 페이지 css 구현<br>
<br>
**조건우 [@KevinJo-Keonwoo](https://github.com/KevinJo-Keonwoo) PM**<br>
✔️ 마이페이지 담당(수강생/프로)<br>
✔️ 후기 페이지 담당(작성/수정)<br>
✔️ 수강생관리 페이지 담당<br>
✔️ job scheduler 이용한 DB  업데이트<br>
✔️ 담당 페이지 css 구현<br>
<br>
**한미래 [@devfuturo](https://github.com/devfuturo) Dev**<br>
✔️ 회원가입, 로그인 페이지 담당<br>
✔️ 서버 오류 담당<br>
✔️ 가입 유효성 검사<br>
✔️ 파일 업로드, 폴더 생성 구현 <br>
✔️ 담당 페이지 css 구현<br>
<br>
**황초연 [@myCYWORLD](https://github.com/myCYWORLD) Dev**<br>
✔️ 아이디/비밀번호 찾기/비밀번호 변경 페이지 담당<br>
✔️ smtp를 활용한 인증코드메일 발송 구현 <br>
✔️ 전반적인 css 담당 <br>
✔️ 메인페이지의 광고배너 구현 <br>
✔️ 담당 페이지 css 구현 <br>
