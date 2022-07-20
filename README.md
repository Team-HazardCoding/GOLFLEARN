# GOLFLEARN
This is a golf platform for golf-beginner(a.k.a. 골린이)

## 프로젝트 개요
### 프로젝트명 : GOLF LEARN

이 서비스는 골린이들을 위한 골프서비스입니다.

<br>
[HAZARD의 노션페이지](https://www.notion.so/1-by-Hazard_Coding-94bca756a1f440e08e96e2ca63f4bb10) 👈 link <br>
### 개발기간
22.06.22(수) ~ 22.07.15(금)
24일간(기획 3일, 설계 7일, 구현 14일)

### 전체일정 프로세스
1 ~ 3일차 : 프로젝트 기획(API설계, 와이어프레임작성), 기능 선정, 역할 분담, 기능별 html 제작<br>
4 ~ 10일차 : 머신러닝 모델 구현, 카메라이미지 업로드기능 구현, 튜터님 피드백(머신러닝관련), Git과 Github 사용<br>
11 ~ 24일차 : 웹 크롤링기능 구현, 댓글기능 구현<br>

## 서비스 기능

### 1. 메인 페이지
- 사진촬영 시 모바일기기나 웹캠에의한 카메라 기능작동
- 촬영 후 촬영이미지를 저장& 클라이언트에게 전송

### 2. 회원가입 페이지
- 사진이미지를 학습시킨 모델을 거쳐 결과를 클라이언트에게 보여줌
-사진 이미지를 진자템플릿을 활용하여 보여줌
- 결과 이미지에 따른 쓰레기 분리수거법 안내

### 3. 로그인 페이지
- 댓글 작성시 아이디 비밀번호 댓글내용을 작성 및 저장
- 작성한 댓글을 수정 및 삭제 시 입력한 비밀번호가 일치시 수정 및 삭제 가능

### 4. 아이디/비밀번호찾기 페이지
- 탭기능을 통한 분리수거 항목별로 소개하는 페이지

### 5. 상세보기 페이지
- 쓰레기별로 대체가능한 방법들을 소개하는 페이지
- 네이버 뷰에서 크롤링해 온 각 항목들을 볼 수 있음

### 6. 마이페이지 페이지

### 7. 리뷰 페이지

### 8. 수강생 관리 페이지

## 구현기능
- 이미지 업로드
- 회원가입 시 유효성검사
- 지역api를 활용한 지역별 레슨필터링
- 레슨 등록 / 수정
- job scheduler를 통한 DB자동 업데이트

## 사용도구
- HTML, CSS
- Javascript
- Java - Jackson, tomcat
- Jquery
- Ajax
- Oracle SQL
- GIT / GIT Hub

### Collaboration & Tools
- Slack
- Figma
- ERD cloud
- starUML

## 팀빌딩 및 역할
- KOSTA 열공러들로 구성
- 해가져도 코딩하는 마인드 탑재
<img width="397" alt="스크린샷 2022-01-04 오전 11 24 18" src="https://user-images.githubusercontent.com/80694130/148001462-dd823e4b-1ed4-4426-95f0-9d61b0c0b71f.png">

### 개발자 (가나다순)<br>
**박종우 @jongsaewoo**<br>
✔️ 사진촬영 페이지 담당<br>
✔️ 사진촬영 및 저장 기능 구현<br>
✔️ 페이지별 오류 수정<br>
✔️ 담당 페이지 css 구현<br>
<br>
**전승현 @cokesh**<br>
✔️ 댓글작성 페이지/탭기능 페이지 담당<br>
✔️ css 총괄 및 각종 이미지 제작<br>
✔️ Git, Github 관리<br>
✔️ 담당 페이지 css 구현<br>
<br>
**조건우 @KevinJo-Keonwoo**<br>
✔️ 결과확인 페이지 담당<br>
✔️ 머신러닝 모델 제작<br>
✔️ 사진저장 및 결과확인 기능 구현<br>
✔️ 담당 페이지 css 구현<br>
<br>
**한미래 @devfuturo**<br>
✔️ 머신러닝 담당<br>
✔️ 크롤링 페이지 오류 및 기능 수정<br>
✔️ 모델 추가 학습관리<br>
✔️ 담당 페이지 css 구현<br>
<br>
**황초연 @myCYWORLD**<br>
✔️ 크롤링 페이지 담당<br>
✔️ 크롤링 기능 구현 및 페이지별 오류 수정<br>
✔️ DB, CRUD 관리<br>
✔️ 담당 페이지 css 구현 <br>

## API소개

![image](https://user-images.githubusercontent.com/79038451/149770791-9d55df52-8aa2-463c-8fa3-763d69cb0b71.png)
![image](https://user-images.githubusercontent.com/79038451/149770825-9df48fe6-e010-4965-ad17-9ea8bcea1d7b.png)
![image](https://user-images.githubusercontent.com/79038451/149770857-999fed27-64c5-48b5-baf7-7cd252970224.png)

## Workflow
![image](https://user-images.githubusercontent.com/79038451/149876685-e4d8f0e7-9f2d-41af-b2ab-1cf26937bb01.png)




