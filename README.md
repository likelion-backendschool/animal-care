# 🐶 Animal-Care
> 반려동물 비대면 진료 서비스

  <p align="center">
      <a href="https://www.youtube.com/watch?v=tJK1YJqomQU"><strong>시연 영상</strong></a>
      <br/>
      <br/>
      이제 급하게 병원에 가지 않아도 진료 받을 수 있습니다.
      <br/>
      수의사와 1:1 화상 채팅을 통해 반려동물을 케어하세요!
      <br/>
  </p>
  
-----

### 🛠 기술 스택

<p align="center"><img width="60%" src="https://user-images.githubusercontent.com/62663502/200250145-4da88b25-6e38-4920-8a1a-b63432e2132f.png"></p>

-----

### ⚙️ 아키텍처 & CI/CD 파이프라인

<p align="center"><img width="60%" src="https://user-images.githubusercontent.com/28800270/200238591-9a2416e0-e7f9-4fdf-abc2-59eb851dcded.png"></p>

-----

### 💾 DB테이블

<p align="center"><img width="60%" src="https://user-images.githubusercontent.com/62663502/200252528-a8274c06-7128-4436-9261-4360b4916c59.png"></p>

-----

### ✨ 구현 기능

#### 회원가입 & 로그인
- `Spring Security`를 사용하여 의사, 멤버 회원가입/로그인 기능 구현
- 의사, 멤버를 `싱글테이블 전략`을 사용하여 회원 관리
- 권한에 따른 페이지 접근 권한 제한

#### 비대면 화상 진료 & 채팅(WebRTC)
- `WebRTC STUN server` 의사 비대면 화상 진료실 생성 구현
- `WebRTC`를 통한 비대면 1:1 화상 채팅 구현
- `WebSocket`을 통한 비대면 진료 시 의사와 환자 채팅 구현
- 비대면 진료를 보며 의사 진단서 작성 구현

#### 진료 예약
- `카카오 지도 API`를 통한 진료 예약 구현
- 멤버,의사 마이페이지에서 진료 예약 내역 `fetch join` 구현
- 의사 마이페이지에서 진단서 확인 및 출력 구현

#### Doctor Q&A
- 회원 질문 생성 `CRUD`, 의사 답변작성 `CRUD` 구현
- `Cookie`를 통한 조회수 중복 방지
- `Ajax`를 통한 게시물 좋아요 기능 구현
- 게시글 페이징 처리

#### 커뮤니티 게시판
- 게시글 및 댓글 `CRUD` 구현
- `Cookie`를 통한 조회수 중복 방지
- `Ajax`를 통한 게시물 좋아요 기능 구현
- 게시글 페이징 처리

-----

### 📈 Branch 전략

<p align="center"><img width="70%" src="https://user-images.githubusercontent.com/62663502/200252893-7117a56c-d79d-4693-bf0c-79242710b3fb.png"></p>

#### 브랜치 컨벤션
- main : 안정되고 배포 가능한 브랜치
- dev : 다음 버전을 대비하여 개발을 진행하는 브랜치
- release/1.0 : 배포를 위한 임시 작업 브랜치
- feature/#{이슈번호}-{기능} : 추가 기능 개발 브랜치
- refactor/#{이슈번호}-{기능} : 리펙토링 브랜치
- hotfix : main 브랜치에서 발생한 버그를 수정하는 브랜치

#### PR Template
- PR명 : 브랜치 명 + 기능
- PR 내용 : DONE, TODO, NOTICE로 구현 한 내용, 추가 해야되는 부분, 특이사항 남기기

-----

### 📘 Commit 컨벤션
   - feat : 새로운 기능 추가
   - fix : 버그 수정
   - docs : 문서 수정
   - style : 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우, 주석 처리 등
   - refactor : 코드 리펙토링
   - test : 테스트 코드, 리펙토링 테스트 코드 추가
   - chore : 빌드 업무 수정, 패키지 매니저 수정

-----

### 📌 업데이트 내역

* v1.0.0 : 첫 번째 버전
