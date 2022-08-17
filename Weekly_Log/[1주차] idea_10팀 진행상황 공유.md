## 팀 구성원, 개인 별 역할

---

+ 김우용 : webRTC공부, API 설계(의사 비대면 진료 페이지, 환자 비대면 진료 페이지)

+ 김치훈 : webRTC공부, API 설계(의사 선택, 의사 상세)

+ 서창민 : DB설계, API 설계(환자 진료 후 페이지)

+ 신상원 : webRTC공부, API 설계(닥터 Q & A, 커뮤니티)

+ 이주원3 : DB설계, API 설계(환자 마이페이지, 의사 마이페이지)

  



## 팀 내부 회의 진행 회차 및 일자

---

+ 1회차(2022.08.01) 디스코드 진행(모두 참여)
  1. DB 설계와 webRTC 구현 방법 고민 => DB(서창민, 이주원), webRTC(김치훈, 신상원, 김우용)
  2. 각 파트 발표(~2022.08.03)

+ 2회차 - 1(2022.08.02) 디스코드 진행(서창민, 이주원)
  1. DB물리적 설계에 대해서 분석
  2. 고민할 점
     + entity 진단서 1개로 할지 or 진료상세, 진료내역 2개로 나눌지
     + 커뮤니티 entity에서 entity를 하나더 추가하는 형식으로 할지 말지
     + 동물 entity에서 entity를 하나더 추가하는 형식으로 할지 말지
     + 동물병원 entity에서 open hours 어떻게 구현할지

+ 2회차 - 2(2022.08.02) 디스코드 진행(김치훈, 신상원, 김우용)
  1. webRTC의 개념 분석
  2. webRTC 예제 코드 분석
  3. 고민할 점
     + webRTC의 기본 내용 정리 한 것
     + 프론트를 어떻게 처리 할 것인가?
     + 프론트의 구현 순서EX) 프론트 먼저 or 프론트 나중에

+ 3회차(2022.08.03) 디스코드 진행(모두 참여)

  1. DB

     + entity 진단서 1개로 할지 or 진료상세, 진료내역 2개로 나눌지 

       => 진단서 1개로 

     + 커뮤니티 entity에서 entity를 하나더 추가하는 형식으로 할지 말지 

       => 추가X ->m:n 관계 테이블 생성 태그 테이블을 따로 만들어서 구현: 태그 강아지, 고양이 등등 article_tag(작성 글 태그) 테이블, article(커뮤니티) 테이블을 m:n 관계로 생성 중간에 article_tag_article 테이블 생성해서 1 : m, n : 1 테이블 구조로 그림

     + 동물 entity에서 entity를 하나더 추가하는 형식으로 할지 말지 

       => animal_breed(동물종) 추가로  진행 

     + 동물병원 entity에서 open hours 어떻게 구현할지 -> string으로 받기

  2. webRTC

     + 구현에 프론트의 비중이 크다 

       => webRTC의 구현을 미루고 나머지 기능을 먼저 빠르게 구현하고 webRTC에 집중 하기로함

  3. Branch 전략에 대한 논의

  4. 커밋 전략에 대한 논의

  5. API 설계해 오기



## 현재까지 개발 과정 요약 (최소 500자 이상)

---

현재까지 개발을 진행하면서 ‘기술적으로 새로 알게된 점, 어려웠던 점, 아쉬운 점' 등을 요약하여 작성해 주세요 🙂

**알게된 점**

1. DB 설계시 개념적 설계 => 논리적 설계 => 물리적 설계를 따라가면서 구조를 만듦

   참고 : https://slidesplayer.org/slide/14144601/

**문제점**

1. webRTC의 구현이 프론트에 취중되어 있음

2. 깃허브 레포지터리의 프로젝트 디렉터리 구조가 이상함

   

**해야할 점**

1. API 설계
2. 코딩 스타일을 맞추기 위해 CRUD 구현 해 오기



## 개발 과정에서 나왔던 질문 (최소 200자 이상)

---



**DB**

	+ 진단서 entity를 1개로 설계할지 아니면 진료상세, 진료내역 2개의 entity로 설계할지
-> 진단서 2개로 나누지 않아도 되기 때문에 진단서 entity 1개로 설계하기로 함

+ 커뮤니티 entity에서 entity를 하나 더 추가하는 형식으로 할지 말지
  -> entity를 추가하지 않고  m:n 관계 테이블 생성
  태그 테이블을 따로 만들어서 구현: 예시) 태그 강아지, 고양이 등등

+ 동물 entity에서 entity를 하나 더 추가하는 형식으로 할지 말지
  -> animal_breed(동물종) 추가로  진행

+  동물병원 entity에서 open hours 어떻게 구현할지
  -> string으로 받기

**webRTC**

+ 구현에 프론트의 비중이 크다
  -> webRTC의 구현을 미루고 나머지 기능을 먼저 빠르게 구현하고 webRTC에 집중 하기로함



## 개발 결과물 공유

---

Github Repository URL : https://github.com/likelion-backendschool/animal-care

webRTC 정리 자료 : https://chlorinated-almandine-fa9.notion.site/08-02-cb269fb9fb204f8aa782fbb503a10a63

DB테이블 설계 : https://miro.com/app/board/uXjVOikSGR8=/?track=true&utm_source=notification&utm_medium=email&utm_campaign=approve-request&utm_content=go-to-miro