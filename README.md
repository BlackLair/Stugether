설계 문서
https://docs.google.com/spreadsheets/d/19hdfWZrPKKwecP1bdD5Ue3CPwwEpMomz6FcXAu1Xd0A/edit?usp=sharing
 - API 설계
 - ERD
 - Session
 - 의존성
 - 일정

# Stugether - 📝 온라인 학습 커뮤니티

Stugether는 블로그, 그룹 커뮤니티에서 다양한 정보를 공유하고, 문제 은행 기능을 통해 나만의 커스텀 문제와 문제집을 만들어 풀고, 다른 회원이 만든 문제집을 풀어볼 수 있는 플랫폼입니다.

- 프로젝트 기간 : 2024.04.03 ~ 2024.05.07
- 프로젝트 인원 : 1명

### 🧰 주요 기술
- Backend - Spring Boot(Java)
- Frontend - JavaScript, JSP, Bootstrap
- Deploy - AWS EC2, Apache Tomcat
- DB - MySQL, MyBatis

### ERD
![image](https://github.com/user-attachments/assets/bb45cd95-491f-4d26-8e26-de831c74c5b8)

## 로그인/회원가입

![image](https://github.com/user-attachments/assets/0a37a563-7169-46e6-a443-5b22e8469eab)
- 아이디, 패스워드를 통해 로그인할 수 있습니다.

![image](https://github.com/user-attachments/assets/40785ff1-a8da-4279-8f7c-e0fef3668e18)
- 계정이 없다면 회원가입을 할 수 있습니다.

## 블로그
![blog main](https://github.com/user-attachments/assets/73d65e0e-6b3e-42a2-a822-fb5a2a233117)
- 로그인 시 자신의 블로그로 이동합니다.
- 좌측에서 카테고리를 추가/삭제할 수 있고, 우측에서 메모를 작성/삭제하거나 드래그/드롭으로 순서를 변경할 수 있습니다.
  
![image](https://github.com/user-attachments/assets/b33659e8-7650-4137-bc6b-572e4c97d8a8)
- 글 작성은 Summernote를 활용하였습니다.

![image](https://github.com/user-attachments/assets/0fc6b274-e86a-419c-a0c2-37dc4996bb63)
- 게시글 페이지에서 글 내용을 확인할 수 있습니다.
- 자신의 블로그의 글일 경우, 글을 삭제할 수 있습니다.
- 하단에 댓글을 작성할 수 있습니다.

## 그룹 커뮤니티
![image](https://github.com/user-attachments/assets/d63e4667-ce42-446f-a865-0823d65d8cf1)
- 상단 메뉴를 통해 그룹 페이지로 이동할 수 있습니다.
- 좌측에서 가입한 그룹들을 확인하거나 새 그룹을 생성할 수 있고, 우측에서 그룹을 검색 및 가입할 수 있습니다.

![image](https://github.com/user-attachments/assets/f28f698e-ba49-43a7-8975-751bcd731522)
- 좌측에서 그룹 생성 시 그룹명, 그룹 소개를 작성할 수 있습니다.
- 우측에서 게시판 카테고리를 추가할 수 있습니다.

![image](https://github.com/user-attachments/assets/445b8d4e-a4df-4e75-b69a-022434f7a45c)
- 그룹 메인 화면입니다. 그룹원들이 작성한 글을 확인할 수 있습니다.
- 다른 사용자의 닉네임을 클릭하면 해당 사용자의 블로그로 이동합니다.

## 문제 은행
![image](https://github.com/user-attachments/assets/092ac808-34aa-4743-8de8-bb6fa4b6e9c1)
- 문제 은행 서비스입니다. 직접 문제와 문제집을 만들고, 타인과 공유할 수 있습니다.

![image](https://github.com/user-attachments/assets/c4a12d9f-56f3-4e85-a6e0-bb7d2f50d9cb)
- 객관식, 주관식 문제를 생성할 수 있습니다.

![image](https://github.com/user-attachments/assets/4ce40f3e-bdbc-491a-98cf-cb908d7e1bfb)
- 문제 번호를 이용해 문제집을 생성할 수 있습니다.

![image](https://github.com/user-attachments/assets/4202c64c-d1d8-4f72-8d6c-deffc6fe7821)
- 문제집 번호, 이름, 제작자명으로 문제집을 검색할 수 있습니다.
- 마음에 드는 문제집을 즐겨찾기할 수 있습니다.

![image](https://github.com/user-attachments/assets/e71c2ce8-09bb-4363-afeb-561b295d00a2)
- 문제집을 풀어볼 수 있습니다.

![image](https://github.com/user-attachments/assets/65d6914a-9254-4d17-9728-d7a2e1e55e74)
- 문제집을 풀면 채점 결과를 볼 수 있고 제출 이력이 저장됩니다.

## 계정 관리
![image](https://github.com/user-attachments/assets/d8ac43a3-08f1-4bae-ac79-cd61d1a037ee)
- 패스워드 인증 시 위와 같이 계정을 관리할 수 있습니다.








