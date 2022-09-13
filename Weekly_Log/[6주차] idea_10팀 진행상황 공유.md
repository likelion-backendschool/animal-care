## 팀 구성원, 개인 별 역할

---

+ 김우용 : WebRTC 자료 조사 & 프로젝트에 구현
+ 김치훈 : 카카오 지도 API 자료 조사 및 프로젝트에 구현
+ 서창민 : 카카오 지도 API 자료 조사 및 프로젝트에 구현
+ 신상원 : WebRTC 자료 조사 & 프로젝트에 구현
+ 이주원3 : WebRTC 자료 조사 & 프로젝트에 구현

## 팀 내부 회의 진행 회차 및 일자

---

- 15회차 스프런트 회의(2022.09.06) 디스코드 진행(모두 참여)
- 16회차 정기회의(2022.09.08) 디스코드 진행(모두 참여)


## 현재까지 개발 과정 요약 (최소 500자 이상)

---

### 구현 내용

- WebRTC 및 온라인 진료실 생성.
  - 각자 개별적으로 WebRTC 구현.
  - 기존 프로젝트에 webRTC 기능 추가
  - 의사일 경우만 온라인 진료실 생성
  
- 지도 API를 이용해 병원 지정 페이지 적용. 
  - 카카오 지도 API 이용.
  - 병원 리스트 지도에 표시
    - 페이징 처리
    - 지도크기에 따른 위도, 경도값 가져오기
    - 이벤트 처리를 통해 지도를 움직일 때 마다 병원 정보 불러오기.
      - Ajax으로 요청 => json으로 응답 => 화면 변경.
  - 지도를 통해 병원 지정 가능.
  
- 그 외
  - 테스트 initDB 코드 제작
    - 공통된 환경 조성을 위해 일부 적용.
    - 병원, 환자, 멤버 등등
  - MemberMyPage 추가 구현
    - 코드 리팩토링을 통해 개발자 친환경 적으로 변경. 
    - 예약 진료 상태명 수정
  - DoctorQna 기능 추가 구현
    - 공통 템플릿, 페이징 적용
    - 일렬번호 로직 개선.
    - 
### 협업 과정 변경
  - Git Issues, Projects, Labels를 활성화
    - 스케줄 관리가 용의.
    - 일의 중요도와 우선순위를 정



## 개발 과정에서 나왔던 질문 (최소 200자 이상)

---

### WebRTC 문제

    - 개발환경외에 배포환경에서도 잘 작동하는지 점검
      - ec2 서버에 임시로 실행해봤지만, 실행 X
      - 보안상 http에선 api 사용에 제한
      - http => https 로 문제해결하는 경우가 많음.
        - https://github.com/ant-media/Ant-Media-Server/issues/1200
    - 개발환경(localhost)에선 작동하는 것으로 확인됨.
      - 우선 개발환경에서 이어서 작업.
      - 의논 결과
        - 당장 도메인 생성이 힘들어 우선 localhost 에 작업

### MapAPI 문제

    - 지구는 둥굴기 때문에 거리식이 따로 존재한다. 
    - 가까운 거리 순으로 조회할 때 어떤 로직으로 실행할 것인가.
      1. db에서 sort 규칙으로 조회 후 paging 하여 응답한다.
      2. db에서 우선 조회 후 java에서 직접 sort 후 paging 하여 응답한다.
    - 의논 결과
      - 현재 대한민국 동물병원 기준 5000곳을 못넘김.
        - java로 모두 가져와 sort하더라도 빠르게 실행 가능.
      - 그럼에도 1번 방법은 sql에 무리를 줄 수 있을까 우려가 됨.
      - 우선 2번 방법을 택하고, 추후 다른 좋은 방법이 있다면 리펙토링 계획.



## 개발 결과물 공유

- Github Repository URL : [https://github.com/likelion-backendschool/animal-care](https://github.com/likelion-backendschool/animal-care)

- wbs: [https://docs.google.com/spreadsheets/d/1tMqUIlmyl2OKtFDN1YOeA4chn9lAe1uLK3NQZCpHmTw/edit#gid=0](https://docs.google.com/spreadsheets/d/1tMqUIlmyl2OKtFDN1YOeA4chn9lAe1uLK3NQZCpHmTw/edit#gid=0)
