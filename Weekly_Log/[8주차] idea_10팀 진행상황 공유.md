## 팀 구성원, 개인 별 역할

---

+ 김우용 : WebRTC WebSocket 1:1 채팅 기능 구현. Online 진료 페이지 내 채팅 UI 구현. 1:1 채팅 기능 구현
+ 김치훈 : 멤버에 애완동물 추가 기능 구현. 동물 관리 api 구현. 동물 관리 뷰 구현
+ 서창민 : 동물이 없을 시 예약서 접근 못하게 수정, Interceptor 사용하여 공통 처리. 
+ 신상원 : DoctorQna 오류 수정, 프론트 부분 일부 수정, 글 추천 기능 구현, 제목, 작성자, 내용 별 검색 기능 구현
+ 이주원3 : WebRTC 화상채팅 온라인 진료실에서 의사, 멤버 UI 다르게 구현. 
## 팀 내부 회의 진행 회차 및 일자

---

+ 19회차 정기회의(2022.09.20) 디스코드 진행(모두 참여)


## 현재까지 개발 과정 요약 (최소 500자 이상)

---

+ Animal
  + 멤버에 애완동물 추가 기능 구현
  + 동물 관리 api 구현. 
  + 동물 관리 뷰 구현


+ Appointment
  + 동물이 없을 시 예약서 접근 못하게 수정
  + Interceptor 사용하여 공통 처리.
  + SecurityConfig에서 사용자의 권한이 없는 경우의 핸들러 추가
    + 현재 예약서만 멤버 권한 부여.
    + 의사가 접근 시 커스텀 에러페이지 전송.


+ Doctor QnA
  + 오류 수정
  + 프론트 부분 일부 수정
  + 글 추천 기능 구현
  + 제목, 작성자, 내용 별 검색 기능 구현


+ WebRTC 추가구현1
  + WebSocket 1:1 채팅 기능 구현. 
  + Online 진료 페이지 내 채팅 UI 구현.
  + 1:1 채팅 기능 구현


+ webRTC 추가구현2
  + 의사와 멤버를 구분해서 UI가 보이도록
  + 진단서와 예약서 연동
  + 진단서폼을 창민님이 만든 form으로 적용
  + 해당 진단서 작성은 한번만 작성할 수 있도록 수정


## 개발 과정에서 나왔던 질문 (최소 200자 이상)

---

+ 멤버에게 animal 추가를 어떻게 해야할까?
+ ➡️ 멤버 마이페이지에서 애완동물을 추가할 수 있도록 구현한다.

+ WebRTC에 채팅 기능을 어떻게 적용할까?
+ ➡️ html에 iFrame을 적용한다. 온라인 진료실 생설할 때 채팅방을 생성하게 한다.

+ WebRTC 온라인 진료실 화면에서 의사, 멤버에 따라서 어떻게 뷰를 보여줄까
+ ➡️ 권한에 따라서 의사에게는 채팅, 예약내역을 50%씩 보여주고 멤버에게는 채팅 너비 100%로 보여준다. 

+ 진단서에 모든 외래키 id를 넣어줘야할까
+ ➡️ 필요한 진단서에서 필요한 내역은 예약서이므로 외래키 예약서id만 넣어준다.

+ WebRTC 온라인 진료실에서 의사, 멤버에 따른 분류를 위해 dto로 정보를 가져와야할까?
+ ➡️ 권한 여부로 간단히 의사인지 멤버인지 확인 가능하다.

## 개발 결과물 공유

---

+ Github Repository URL : [https://github.com/likelion-backendschool/animal-care](https://github.com/likelion-backendschool/animal-care)
+ wbs: [https://docs.google.com/spreadsheets/d/1tMqUIlmyl2OKtFDN1YOeA4chn9lAe1uLK3NQZCpHmTw/edit#gid=0](https://docs.google.com/spreadsheets/d/1tMqUIlmyl2OKtFDN1YOeA4chn9lAe1uLK3NQZCpHmTw/edit#gid=0)
