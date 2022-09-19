## 팀 구성원, 개인 별 역할

---

+ 김우용 : WebSocket 1:1 채팅 기능 구현.
+ 김치훈 : 로그인 validation 추가 및 세부사항 수정.
+ 서창민 : 진단서 Table 수정 및 진단서 출력 기능 추가.
+ 신상원 : DoctorQna 추천, 검색 기능 구현.
+ 이주원3 : WebRTC 화상채팅 온라인 진료실 생성 기능 추가.


## 팀 내부 회의 진행 회차 및 일자

---

+ 17회차 정기회의(2022.09.13) 디스코드 진행(모두 참여)
+ 18회차 멘토링(2022.09.14) 디스코드 진행(모두 참여)


## 현재까지 개발 과정 요약 (최소 500자 이상)

---

+ 진단서 table 전면 수정 
  + 예약서만 오직 관계를 가짐 (단방향)
    + 양방향으로 할 시, lasy가 아닌 Eager로 불러와짐.
    + 성능 저하의 이유로 단방향.
+ 진단서 출력기능 추가
+ 공통 레이아웃 적용


+ Validation 추가
+ 로그인시 파일 생기던 오류 수정
+ 로그인 폼 수정
+ session 오류로 인한 뷰 안되던 것 수정


+ 예약서 페이지 구현
+ 테스트 데이터 일부 추가
+ 지도 검색 기능 추가 및 사용자 편의 일부 개선


+ WebSocket, STOMP 기반 1:1 채팅 기능 구현

## 개발 과정에서 나왔던 질문 (최소 200자 이상)

---

+ WebRTC는 https 에서만 동작하기 때문에, 로컬이나 도메인 없는 배포로는(http) 화상 회의가 잘 동작하는지 확인하기가 어렵다. 방법이 없을까요?
+ ➡️ ngrok을 이용하여 빠르게 테스트 해볼 수 있을 것. (방화벽 넘어서 외부에서 로컬에 접속 가능하게 하는 터널 프로그램이라고 할 수 있다)

+ 지도 API 관련 해서, 위치를 보여주면 위치의 움직임에 따라 이벤트가 발생해 비동기로 자료를 보내 병원 정보를 업데이트 하는 방식으로 구현 중인데 이러한 방식이 부담이 많이 가는 방식은 아닌지? 보통은 어떤 식으로 구현을 하는지?
+ ➡️ 코드를 봐야 정확히 답변을 드릴 수 있겠지만, 크게 상관은 없을 것 같다. 하지만 성능 개선을 위해 최적화 & 읽기 전용으로 구성하는 것을 생각 해 볼 수 있을 것 같다. 자세한 내용은 코드를 보고 개인적으로 답변을 드리겠다.


## 개발 결과물 공유

---

+ Github Repository URL : [https://github.com/likelion-backendschool/animal-care](https://github.com/likelion-backendschool/animal-care)
+ wbs: [https://docs.google.com/spreadsheets/d/1tMqUIlmyl2OKtFDN1YOeA4chn9lAe1uLK3NQZCpHmTw/edit#gid=0](https://docs.google.com/spreadsheets/d/1tMqUIlmyl2OKtFDN1YOeA4chn9lAe1uLK3NQZCpHmTw/edit#gid=0)